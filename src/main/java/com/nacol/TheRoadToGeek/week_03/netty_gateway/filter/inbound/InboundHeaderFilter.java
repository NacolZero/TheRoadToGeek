package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.UUID;

public class InboundHeaderFilter implements HttpInboundFilter {

    private String serverCode;

    public InboundHeaderFilter(String serverCode) {
        this.serverCode = serverCode;
    }

    @Override
    public void filter(HttpRequestDto requestDto, ChannelHandlerContext ctx) {
        requestDto.getCustomHeaders().put("requestid", serverCode + UUID.randomUUID());
    }

}
