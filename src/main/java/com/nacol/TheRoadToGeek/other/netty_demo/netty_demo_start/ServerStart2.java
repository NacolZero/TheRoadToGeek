package com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_start;

import com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_server.NettyServer2;

public class ServerStart2 {

    public static void main(String[] args) throws Exception {
        //启动server服务
        new NettyServer2().bind(8080);
    }
}
