package com.nacol.TheRoadToGeek.common.http.client.nettyclient;

import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SimpleClientHandler extends SimpleChannelInboundHandler<HttpResponseDto> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接关闭! ");
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpResponseDto requestDto) throws Exception {
        System.out.println("------------------------------------------------ channelRead0 : " +  requestDto);
    }

}
