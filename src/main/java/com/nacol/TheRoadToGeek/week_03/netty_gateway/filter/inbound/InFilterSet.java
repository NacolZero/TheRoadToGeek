package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 批量 filter 处理
 */
public class InFilterSet {

    List<InboundHeaderFilter> filters;

    public InFilterSet(List<InboundHeaderFilter> filters) {
        this.filters = filters;
    }

    public void batchFilter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        filters.forEach(f->f.filter(fullHttpRequest, ctx));
    }

}
