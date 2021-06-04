package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;

import java.util.UUID;

public class InboundHeaderFilter implements HttpInboundFilter {

    private String serverCode;

    public InboundHeaderFilter(String serverCode) {
        this.serverCode = serverCode;
    }

    @Override
    public void filter(HttpRequestDto requestDto, ChannelHandlerContext ctx) {
        requestDto.getCustomHeaders().put(serverCode + "responeseid", UUID.randomUUID().toString());
    }

}
