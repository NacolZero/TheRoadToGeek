package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpOutboundFilter {

    public void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx);

}
