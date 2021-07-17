package com.nacol.TheRoadToGeek.week_11.auto_expire;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description
 * 目前实现：
 * NacolCache：缓存类，提供存储获取功能。
 * CacheEntity：对 value 的重新封装，新增了 createTime，为了防止重复操作一个 key，错误地自动释放。
 * AutuExpireTask：开启线程关闭自动释放的 key / value。
 *
 * 一些想法：
 * 1. 开启线程后，使用的 LockSupport.park()，记得这个函数是要释放 CPU 和线程资源，应该没那么消耗资源，但是心里还是有点不踏实。
 * 2. 过期时间并不那么精准;
 * 3. 也可以使用定时任务定期扫描过期的元素。
 * 2.1. 当缓存的元素足够多后，一直扫描 values 性能会非常之差；
 * 2.2. 优化方式：额外创建一个数组，用于维护过期时间。数组中存放的对象（期时间和 key），过期时间排序，
 *      最小的排最前面，也就是说，离过期时间越近的拍最前面，那么每次就只用扫描数组中的第一个元素即可。（有点类似 RabbitMQ 的过期）
 *      虽然没看 guava 源码，不过之所以 GuavaCache 每个 cache 对象只允许一个有一种过期时间，很有可能就是为了放到数组中，
 *      不需要重排序只看第一个元素即可。
 */
public class AutoExpireMain {

    public static void main(String[] args) throws InterruptedException {
        NacolCache<String, Object> cache = new NacolCache<>();
        //普通存储和获取
        cache.put("name", "nacol");
        System.out.println(cache.get("name"));
        //存储数据，超时自动释放
        cache.expirePut("age", 18, 1000);
        System.out.println(cache.get("age"));
        TimeUnit.MILLISECONDS.sleep(1002);
        System.out.println(cache.get("age"));


        //单纯 put 测试性能
        testNacolCache();
        testGuavaCache();
    }

    public static void testNacolCache() {
        NacolCache<String, Object> cache = new NacolCache<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.expirePut("age" + i, i, 10000);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void testGuavaCache() {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10000, TimeUnit.MILLISECONDS)
                .build();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cache.put("age" + i, i);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
