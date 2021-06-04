package com.nacol.TheRoadToGeek.common.http.gateway.netty.filter.in.impl;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.gateway.netty.filter.in.IHttpInboundFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.UUID;

public class InboundHeaderFilterI implements IHttpInboundFilter {

    @Override
    public void filter(FullHttpRequest request) {
        request.headers().add("requestid", UUID.randomUUID().toString());
    }

}
