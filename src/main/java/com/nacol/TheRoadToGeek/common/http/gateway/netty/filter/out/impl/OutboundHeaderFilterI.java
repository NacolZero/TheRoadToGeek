package com.nacol.TheRoadToGeek.common.http.gateway.netty.filter.out.impl;

import com.nacol.TheRoadToGeek.common.http.gateway.netty.filter.out.IHttpOutboundFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.UUID;

public class OutboundHeaderFilterI implements IHttpOutboundFilter {

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().add("requestid", UUID.randomUUID().toString());
    }

}
