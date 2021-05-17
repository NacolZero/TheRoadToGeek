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
        config.put("cargo", Arrays.asList("localhost:9001", "localhost:9002"));
        //运单服务
        config.put("waybill", Arrays.asList("localhost:9003", "localhost:9004"));
        //航班服务
        config.put("flight", Arrays.asList("localhost:9005", "localhost:9006"));
    }

}
