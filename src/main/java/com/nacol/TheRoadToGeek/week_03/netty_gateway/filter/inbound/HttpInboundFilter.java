package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpInboundFilter {

    public void filter(HttpRequestDto fullHttpRequest, ChannelHandlerContext ctx);

}
