package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpInboundFilter {

    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);

}
