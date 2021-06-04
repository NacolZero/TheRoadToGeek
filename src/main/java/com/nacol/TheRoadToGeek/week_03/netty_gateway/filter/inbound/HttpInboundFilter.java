package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;

public interface HttpInboundFilter {

    void filter(HttpRequestDto requestDto, ChannelHandlerContext ctx);

}
