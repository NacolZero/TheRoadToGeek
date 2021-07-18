package com.nacol.TheRoadToGeek.week_11.pubsub;

import redis.clients.jedis.JedisPool;

public class RunMain {

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool();
        String channelName = "say";
        Publisher publisher = new Publisher(jedisPool, channelName);
        Subscriber subscriber = new Subscriber(jedisPool, channelName);
        subscriber.subscrib();
        publisher.publish("你好");
        publisher.publish("老铁");
        publisher.publish("我是你的老邻居，老王！");
    }

}
