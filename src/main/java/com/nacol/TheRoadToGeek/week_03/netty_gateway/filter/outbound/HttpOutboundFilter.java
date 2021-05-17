package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

public interface HttpOutboundFilter {

    public void filter(HttpResponseDto responseDto, ChannelHandlerContext ctx);

}
