package com.nacol.TheRoadToGeek.week_11.redis_demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 排行榜
 */
public class RankingMain {

    public static void main(String[] args) {
        try (JedisPool jedisPool = new JedisPool();
             Jedis jedis = jedisPool.getResource();){
            for (int i = 0; i < 100; i++) {
                //分数作为排序标准
                int score = new Random().nextInt(100);
                String content = i + "号同学得到了" + score + "分";
                //倒序
                jedis.zadd("rank", -score, content);
                //每出一个成绩，就输出一次，验证排行榜顺序
                System.out.println(jedis.zrange("rank", 0, -1));
            }
        }
    }
}
