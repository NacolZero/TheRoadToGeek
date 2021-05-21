package com.nacol.TheRoadToGeek.week_04.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NacolReentrantCounter {

    private int sum = 0;

    //可重入锁
    //true公平锁
    private Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        try {
            lock.lock();
            return ++sum;
        } finally {
            lock.unlock();
        }
    }

}

