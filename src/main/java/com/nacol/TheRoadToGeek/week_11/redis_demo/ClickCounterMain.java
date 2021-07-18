package com.nacol.TheRoadToGeek.week_11.redis_demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 基于 HLL 实现点击量计数；
 */
public class ClickCounterMain {

    private static final String PV = "PV";

    public static void main(String[] args) {
        try(JedisPool jedisPool = new JedisPool();
            Jedis jedis = jedisPool.getResource()) {
            //模拟随机ID访问了一万次
            for (int i = 0; i < 10000; i++) {
                Integer userId = new Random().nextInt(10000);
                jedis.pfadd(PV,  userId.toString());
            }
            //统计
            System.out.println(jedis.pfcount(PV));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
