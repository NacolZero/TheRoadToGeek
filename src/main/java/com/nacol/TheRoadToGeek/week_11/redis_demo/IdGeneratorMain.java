package com.nacol.TheRoadToGeek.week_11.redis_demo;

import redis.clients.jedis.Jedis;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/18
 * @Description 分布式 id 生成器
 */
public class IdGeneratorMain {

    public static class IdGenerator {
        private static Jedis jedis = new Jedis("localhost", 6379);
        public static long getId() {
            return jedis.incr("id");
        }
    }

    private static final int CORE_NUM = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        Executor threadPool = Executors.newFixedThreadPool(CORE_NUM);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName() + "获取 id :" + IdGenerator.getId());
            });
        }
    }

}
