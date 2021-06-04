package com.nacol.TheRoadToGeek.common.http.client_v2.netty;

import io.netty.channel.Channel;

public interface BaseNettyClient {

    void start() throws Exception;

    Channel getChannel();
}
