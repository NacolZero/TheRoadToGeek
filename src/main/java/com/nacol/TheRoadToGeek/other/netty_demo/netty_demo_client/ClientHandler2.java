package com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_client;

import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler2 extends SimpleChannelInboundHandler<HttpResponseDto> {

    //处理服务端返回的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpResponseDto response) throws Exception {
        System.out.println("接受到server响应数据 2: " + JSONObject.toJSONString(response));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}