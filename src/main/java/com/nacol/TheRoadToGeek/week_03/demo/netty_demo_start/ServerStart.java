package com.nacol.TheRoadToGeek.week_03.demo.netty_demo_start;

import com.nacol.TheRoadToGeek.week_03.demo.netty_demo_server.NettyServer;

public class ServerStart {

    public static void main(String[] args) throws Exception {
        //启动server服务
        new NettyServer().bind(8080);
    }
}
