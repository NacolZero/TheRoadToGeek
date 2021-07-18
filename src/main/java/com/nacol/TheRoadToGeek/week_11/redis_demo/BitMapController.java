package com.nacol.TheRoadToGeek.week_11.redis_demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author Nacol  
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 基于 Bitmap 实现 id 去重；
 */
public class BitMapController {

    private static final String KEY = "dedup";

    public static void main(String[] args) {
        try(JedisPool jedisPool = new JedisPool();
            Jedis jedis = jedisPool.getResource()) {
            //模拟插入 100 个 id
            for (int i = 0; i < 100; i++) {
                jedis.setbit(KEY, i, true);
            }
            //获取已经存在的 id
            System.out.println(jedis.getbit(KEY, 99));
            //获取不存在的 id
            System.out.println(jedis.getbit(KEY, 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
