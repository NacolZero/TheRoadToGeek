package io.nacol.rpc.netty.handler;

import com.alibaba.fastjson.JSON;
import io.nacol.rpc.api.RpcResponse;
import io.nacol.rpc.netty.common.RpcMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CountDownLatch;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/3
 * @Description 使用信号量拿结果
 */
@Log4j2
public class RpcSynHandler extends SimpleChannelInboundHandler<RpcMessage> {

    private CountDownLatch countDownLatch;
    private RpcResponse rpcResponse;

    public RpcSynHandler(){}

    public RpcSynHandler(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage) throws Exception {
        log.info("get response content : {}", rpcMessage.getContent());
        log.info("get response length : {}", rpcMessage.getLength());

        RpcResponse rpcResponse = JSON.parseObject(new String(rpcMessage.getContent(), CharsetUtil.UTF_8), RpcResponse.class);
        log.info("parse response : {}", JSON.toJSONString(rpcMessage));

        this.rpcResponse = rpcResponse;
//        channelHandlerContext.writeAndFlush(rpcMessage);
        countDownLatch.countDown();
        log.info("return response");
    }

    public RpcResponse getRpcResponse() throws InterruptedException {
        countDownLatch.await();
        log.info("get Response");
        return this.rpcResponse;
    }

}
