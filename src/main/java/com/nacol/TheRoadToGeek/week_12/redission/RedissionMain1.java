package com.nacol.TheRoadToGeek.week_12.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissionMain1 {

    public static void main(String[] args) throws InterruptedException {
        handleMap();
    }

    public static void handleMap() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        final RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("RMapClock");
        RMap<String, String> rmap = client.getMap("redission-map");

        try {
            lock.lock();
            for (int i = 0; i < 20; i++) {
                rmap.put("key-" + i, i*2 + "");
            }


        } finally {
            lock.unlock();
        }
        while(true){
            TimeUnit.SECONDS.sleep(2);
            System.out.println(rmap.get("key-10"));
        }

    }

}
