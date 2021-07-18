package com.nacol.TheRoadToGeek.week_11.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 发布者
 */
public class Publisher {

    private JedisPool jedisPool;

    private String channelName;

    public Publisher(JedisPool jedisPool, String channelName) {
        this.jedisPool = jedisPool;
        this.channelName = channelName;
    }

    public void publish(String msg) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.publish(this.channelName, msg);
        }
    }

}
