package com.nacol.TheRoadToGeek.common.http.client.nettyclient;

import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NacolClientHandler extends SimpleChannelInboundHandler<HttpResponseDto> {

    //处理服务端返回的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpResponseDto response) throws Exception {
        log.info("Nacol client 接受到 server 响应数据: ", JSONObject.toJSONString(response));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("-------------------channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("-------------------exceptionCaught");
        ctx.close();
    }


}