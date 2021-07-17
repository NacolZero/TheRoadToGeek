package com.nacol.TheRoadToGeek.week_11.auto_expire;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NacolCache<K, V> {

    private ConcurrentHashMap<K, CacheEntity<V>> cache;
    private static ExecutorService ThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public NacolCache() {
        cache = new ConcurrentHashMap<>();
    }

    public void put(K key, V value) {
        cache.put(key, new CacheEntity(value));
    }

    public V get(K key) {
        return Optional.ofNullable(cache)
                .map(o->o.get(key))
                .map(o->o.getValue())
                .orElse(null);
    }

    public void expirePut(K key, V value, long milliSeconds) {
        CacheEntity<V> cacheEntity = new CacheEntity(value);
        ThreadPool.execute(new AutuExpireTask(cache, key, cacheEntity, milliSeconds));
        cache.put(key, cacheEntity);

    }

}
