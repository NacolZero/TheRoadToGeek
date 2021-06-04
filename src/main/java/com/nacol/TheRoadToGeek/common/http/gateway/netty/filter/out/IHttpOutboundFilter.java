package com.nacol.TheRoadToGeek.common.http.gateway.netty.filter.out;

import io.netty.handler.codec.http.FullHttpResponse;

public interface IHttpOutboundFilter {

    void filter(FullHttpResponse response);

}
