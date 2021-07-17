package com.nacol.TheRoadToGeek.week_11.jedis;

import redis.clients.jedis.Jedis;

public class JedisMain {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println(jedis);
        jedis.set("currentTime", new Long(System.currentTimeMillis()).toString());
        System.out.println(jedis.get("currentTime"));
    }

}
