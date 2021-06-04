package com.nacol.TheRoadToGeek.common.http.gateway.netty.filter.in;

import io.netty.handler.codec.http.FullHttpRequest;

public interface IHttpInboundFilter {

    void filter(FullHttpRequest request);

}
