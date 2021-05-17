package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.UUID;

public class OutboundHeaderFilter implements HttpOutboundFilter{

    private String serverCode;

    public OutboundHeaderFilter(String serverCode) {
        this.serverCode = serverCode;
    }

    @Override
    public void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx) {
        fullHttpResponse.headers().set(serverCode + "responeseid", UUID.randomUUID());
    }

}
