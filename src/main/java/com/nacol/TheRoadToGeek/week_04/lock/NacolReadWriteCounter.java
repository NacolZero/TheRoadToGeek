package com.nacol.TheRoadToGeek.week_04.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class NacolReadWriteCounter {

    private int num = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public int addAndGet() {
        try {
            lock.writeLock().lock();
            return ++num;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getNum() {
        try {
            lock.readLock().lock();
            return this.num;
        } finally {
            lock.readLock().unlock();
        }
    }

}
