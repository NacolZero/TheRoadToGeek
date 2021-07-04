package io.nacol.rpc.demo.provider.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.nacol.rpc.api.RpcRequest;
import io.nacol.rpc.api.RpcResponse;
import io.nacol.rpc.netty.common.RpcMessage;
import io.nacol.rpc.demo.provider.utils.SpringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/1
 * @Description RPC - 服务端 - 处理器
 */
@Log4j2
@Component
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage) throws Exception {
        log.info("Rpc netty server recevie meesage : {}", JSON.toJSON(rpcMessage));
        log.info("Rpc netty server recevie meesage : {}", new String(rpcMessage.getContent(), CharsetUtil.UTF_8));

        RpcRequest rpcRequest = JSON.parseObject(new String(rpcMessage.getContent(), CharsetUtil.UTF_8), RpcRequest.class);
        log.info("Rpc request : {}", JSON.toJSON(rpcRequest));

        RpcResponse rpcResponse = invoke(rpcRequest);

        byte[] bytes = JSON.toJSONString(rpcResponse).getBytes(CharsetUtil.UTF_8);
        RpcMessage rspMsg = new RpcMessage()
                .setContent(bytes)
                .setLength(bytes.length);

        channelHandlerContext.writeAndFlush(rspMsg).sync();
        log.info("netty server response.");
    }

    private RpcResponse invoke(RpcRequest rpcRequest) {
        RpcResponse rpcResponse = new RpcResponse();
        try {
            // 作业1：改成泛型和反射：见 io.nacol.rpcfx.util.ReflectionClazzFinder
            // 此处依然使用 spring 容器类查找
            Object serverObj = SpringUtils.getBean(rpcRequest.getServiceClass());
            Method method = resolveMethodFromClass(serverObj.getClass(), rpcRequest.getMethod());
            Object result = method.invoke(serverObj, rpcRequest.getParams());
            rpcResponse.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            rpcResponse.setStatus(true);
            return rpcResponse;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            rpcResponse.setStatus(false);
            rpcResponse.setException(e);
            return rpcResponse;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

    //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端接收数据完毕");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("服务端捕获异常异常 : " + cause);
        ctx.close();
    }

    //客户端去和服务端连接成功时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
}
