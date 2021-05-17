package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.UUID;

public class InboundHeaderFilter implements HttpInboundFilter {

    private String serverCode;

    public InboundHeaderFilter(String serverCode) {
        this.serverCode = serverCode;
    }

    @Override
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().set("requestid", serverCode + UUID.randomUUID());
    }

}
