package com.nacol.TheRoadToGeek.week_03.netty_gateway.config;

import java.util.HashSet;
import java.util.Set;

public class IpConfig {

    public static Set<String> whiteList = new HashSet<>();

    static {
        whiteList.add("127.0.0.1");
        whiteList.add("127.0.0.2");
        whiteList.add("127.0.0.3");
        whiteList.add("127.0.0.4");
    }
}
