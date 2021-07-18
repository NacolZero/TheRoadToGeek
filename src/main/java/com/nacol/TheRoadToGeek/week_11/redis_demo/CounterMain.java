package com.nacol.TheRoadToGeek.week_11.redis_demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 模拟减少库粗
 */
public class CounterMain {

    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool();
             Jedis jedis = jedisPool.getResource();){
            //初始化库存
            jedis.set("num", "10");
            for (int i = 0; i < 100; i++) {
                new Thread(()->{
                    Jedis tempJedis = new Jedis("localhost", 6379);
                    long result = tempJedis.decr("num");
                    if (result > 0) {
                        System.out.println(Thread.currentThread().getName() + "拿到库存，还剩下 ：" + result);
                    } else {
                        System.out.println(Thread.currentThread().getName() + "慢了");
                    }
                }).start();
            }
        }
    }

}
