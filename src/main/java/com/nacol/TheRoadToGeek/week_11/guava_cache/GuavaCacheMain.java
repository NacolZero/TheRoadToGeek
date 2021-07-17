package com.nacol.TheRoadToGeek.week_11.guava_cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //类似于 map 的缓存
        setAndGet();
        //设置缓存最大容量
        maxNumSize();
        //设置固定过期时间
        setFixExpire();
        //设置访问过期时间
        setAccessExpire();
        //弱引用
        week();
        //删除
        invalidate();
        //监听被删除: expire 不会触发，删除会触发，超过 maxsize 会触发
        listener();
        //双线 get：
        // 当 get 时
        // 缓存中有 key 取缓存
        // 缓存中没 key 则开启线程用另外的逻辑取值
        get();
        //统计
        statistics();
    }

    private static void statistics() {
        System.out.println("--------------------------statistics----------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("key", "value");
        cache.getIfPresent("key");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");

        System.out.println(cache.stats());
    }

    private static void get() throws ExecutionException {
        System.out.println("--------------------------listener----------------------------");
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                return "callable return result";
            }
        };

        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("key", "value");
        Object result = cache.get("key", callable);
        Object result2 = cache.get("keykey", callable);

        System.out.println(result);
        System.out.println(result2);

    }

    private static void listener() throws InterruptedException {
        System.out.println("--------------------------listener----------------------------");
        RemovalListener<String, Object> listener = new RemovalListener<String, Object>() {
            public void onRemoval(RemovalNotification<String, Object> notification) {
                //当被删除后可以触发一些事件
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        };

        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .expireAfterWrite(4, TimeUnit.MILLISECONDS)
                .removalListener(listener)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
    }

    private static void invalidate() {
        System.out.println("--------------------------invalidate----------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        for (int i = 0; i < 30; i++) {
            cache.put("key" + i, i);
        }
        List<String> deleteKeys = new ArrayList();
        for (int i = 0; i < 10; i++) {
            //单个删除
            cache.invalidate("key" + i);
            deleteKeys.add("key" + (i + 10));
        }
        //删除集合中的 key
        cache.invalidateAll(deleteKeys);
        //全部删除
        cache.invalidateAll();
    }

    private static void week() {
        System.out.println("--------------------------setAccessExpire----------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .weakKeys()
                .weakValues()
                .build();
        Object value = new Object();
        cache.put("key", value);
        //强引用时 GC
        System.gc();
        System.out.println("强引用 GC 后 : " + cache.getIfPresent("key"));
        //弱引用时 GC
        value = new Object();
        System.gc();
        System.out.println("弱引用 GC 后 : " + cache.getIfPresent("key"));
    }

    private static void setAccessExpire() throws InterruptedException {
        System.out.println("--------------------------setAccessExpire----------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(10, TimeUnit.MILLISECONDS)
                .build();
        cache.put("key", "value");
        for (int i = 0; i < 20; i++) {
            System.out.println(cache.getIfPresent("key"));
            if(i == 15) {
                TimeUnit.MILLISECONDS.sleep(10);
            }
        }
    }

    private static void setFixExpire() throws InterruptedException {
        System.out.println("--------------------------setFixExpire----------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(4, TimeUnit.MILLISECONDS)
                .build();
        cache.put("key", "value");
        for (int i = 0; i < 3; i++) {
            System.out.println(cache.getIfPresent("key"));
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

    private static void maxNumSize() {
        System.out.println("---------------------------maxNumSize---------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build();
        cache.put("k1", "v1");
        cache.put("k2", "v2");
        cache.put("k3", "v3");
        cache.put("k4", "v4");
        cache.put("k5", "v5");
        cache.put("k6", "v6");

        System.out.println(cache.getIfPresent("k1"));
        System.out.println(cache.getIfPresent("k2"));
        System.out.println(cache.getIfPresent("k3"));
        System.out.println(cache.getIfPresent("k4"));
        System.out.println(cache.getIfPresent("k5"));
        System.out.println(cache.getIfPresent("k6"));
    }

    private static void setAndGet() {
        System.out.println("---------------------------setAndGet---------------------------");
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("name", "nacol");
        System.out.println(cache.getIfPresent("name"));
    }



}
