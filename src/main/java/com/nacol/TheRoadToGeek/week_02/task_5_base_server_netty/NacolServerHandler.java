package com.nacol.TheRoadToGeek.week_02.task_5_base_server_netty;


import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpSourceCacheEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NacolServerHandler extends ChannelInboundHandlerAdapter{

    //接受client发送的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpRequestDto request = (HttpRequestDto) msg;
        log.info("接收到客户端信息: {} ", JSONObject.toJSONString(request));
        handle(ctx, request);
    }

    private void handle(ChannelHandlerContext ctx, HttpRequestDto requestDto) {
        String uri = requestDto.getUri();
        HttpResponseDto response = new HttpResponseDto();
        if(HttpSourceCacheEnum.TEST.serviceCode.contains("test")) {
            response.setResultData("server 端 test 执行完毕 ^@^")
                .setStatus(200);
        } else if (HttpSourceCacheEnum.LOGIN.serviceCode.equals(uri)) {
            response.setResultData("server 端login 执行完毕 ^@^")
                .setStatus(200);
        } else if (HttpSourceCacheEnum.PAY.serviceCode.equals(uri)) {
            response.setResultData("server 端 pay 执行完毕 ^@^")
                .setStatus(200);
        } else {
            response.setResultData("server 端未获取到有效 uri")
                .setStatus(500);
        }
        ctx.writeAndFlush(response);
    }

    //通知处理器最后的channelRead()是当前批处理中的最后一条消息时调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("Nacol 服务端接收数据完毕");
        ctx.flush();
    }

    //读操作时捕获到异常时调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    //客户端去和服务端连接成功时触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush("hello client");
    }
}