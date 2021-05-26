package com.nacol.TheRoadToGeek.week_03.netty_gateway;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.GatewayConfig;

public class NettyGatewayRun {

    public static void main(String[] args) {
        run(9989, GatewayConfig.CARGO);
    }

    public static void run(int port, String serverName) {
        NettyHttpServer server = new NettyHttpServer(port, serverName);
        try {
            server.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
