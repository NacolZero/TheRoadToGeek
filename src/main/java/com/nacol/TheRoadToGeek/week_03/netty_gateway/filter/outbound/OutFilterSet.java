package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound.HttpOutboundFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.List;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 批量 filter 处理
 */
public class OutFilterSet {

    List<HttpOutboundFilter> filters;

    public OutFilterSet(List<HttpOutboundFilter> filters) {
        this.filters = filters;
    }

    public void batchFilter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx) {
        filters.forEach(f->f.filter(fullHttpResponse, ctx));
    }

}
