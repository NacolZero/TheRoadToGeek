package com.nacol.TheRoadToGeek.week_11.auto_expire;

public class CacheEntity<V> {

    /**
     * 值
     */
    private V value;

    /**
     * 创建事件
     */
    private long createTime;

    public CacheEntity(V value) {
        this.createTime = System.currentTimeMillis();
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public long getCreateTime() {
        return createTime;
    }

}
