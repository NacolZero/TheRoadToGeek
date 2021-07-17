package com.nacol.TheRoadToGeek.week_11.auto_expire;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;

public class AutuExpireTask<K, V> implements Runnable {

    private ConcurrentHashMap<K, CacheEntity<V>> cache;
    private K key;
    private CacheEntity<V> inputCache;
    private long milliSeconds;

    public AutuExpireTask(ConcurrentHashMap<K, CacheEntity<V>> cache, K key, CacheEntity inputCache, long milliSeconds) {
        this.cache = cache;
        this.key = key;
        this.inputCache = inputCache;
        this.milliSeconds = milliSeconds;
    }

    @Override
    public void run() {
        LockSupport.parkNanos(1000000 * milliSeconds);
        remove();
    }

    /**
     * 因为需要多次 get / remove 等操作，
     * 非原子性，因此需要加锁。
     * 又考虑重复 key 的几率可能会很小，因此使用 synchronized
     */
    public synchronized void remove() {
        //如果提前被显示地删除，那么则不操作；
        if (!cache.containsKey(key)) {
            return;
        }
        //如果 createTime 不一样，则说明被重新添加同样的 key 的元素, 那么不应该在此处移除。
        CacheEntity<V> curCache = cache.get(key);
        if (curCache.getCreateTime() == inputCache.getCreateTime()) {
            cache.remove(key);
        }
    }
}
