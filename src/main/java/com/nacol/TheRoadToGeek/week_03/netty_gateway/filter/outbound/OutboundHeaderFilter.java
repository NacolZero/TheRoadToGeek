package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.UUID;

public class OutboundHeaderFilter implements HttpOutboundFilter{

    private String serverCode;

    public OutboundHeaderFilter(String serverCode) {
        this.serverCode = serverCode;
    }

    @Override
    public void filter(HttpResponseDto responseDto, ChannelHandlerContext ctx) {
        responseDto.getCustomHeaders().put(serverCode + "responeseid", UUID.randomUUID().toString());
    }

}
