package com.nacol.TheRoadToGeek.week_11.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 接收者
 */
public class Subscriber {

    private JedisPool jedisPool;

    private String channelName;


    public Subscriber(JedisPool jedisPool, String channelName) {
        this.jedisPool = jedisPool;
        this.channelName = channelName;
    }

    public void subscrib() {
        new Thread(()->{
            System.out.println("开始接收消息");
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.subscribe(init(), channelName);
            }
        }, "sub-thread").start();
    }

    private JedisPubSub init() {
        return new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.printf("收到消息 %s : %s\n", channel, message);
            }
        };
    }

}
