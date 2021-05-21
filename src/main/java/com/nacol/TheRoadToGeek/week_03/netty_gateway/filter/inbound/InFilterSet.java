package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 入站 filter 集合处理器
 */
public class InFilterSet {

    List<InboundHeaderFilter> filters;

    public InFilterSet(List<InboundHeaderFilter> filters) {
        this.filters = filters;
    }

    public void batchFilter(HttpRequestDto request, ChannelHandlerContext ctx) {
        filters.forEach(f->f.filter(request, ctx));
    }

}
