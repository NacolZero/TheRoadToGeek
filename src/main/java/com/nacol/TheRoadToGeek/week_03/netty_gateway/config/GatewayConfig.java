package com.nacol.TheRoadToGeek.week_03.netty_gateway.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GatewayConfig {

    public static final String CARGO = "cargo";
    public static final String WAYBILL = "waybill";
    public static final String FLIGHT = "flight";

    public static final Map<String, List<String>> config = new HashMap<>();
    static {
        //货物服务
        config.put("cargo", Arrays.asList("127.0.0.1:9001", "127.0.0.1:9002"));
        //运单服务
        config.put("waybill", Arrays.asList("127.0.0.1:9003", "127.0.0.1:9004"));
        //航班服务
        config.put("flight", Arrays.asList("127.0.0.1:9005", "127.0.0.1:9006"));
    }

}
