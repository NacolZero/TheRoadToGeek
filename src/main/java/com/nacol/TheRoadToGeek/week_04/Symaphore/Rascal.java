package com.nacol.TheRoadToGeek.week_04.Symaphore;

import java.util.concurrent.Semaphore;

public class Rascal {

    Semaphore semaphore = new Semaphore(6);

    public void doSemeThing() throws InterruptedException {
        semaphore.acquire(4);
        System.out.println("老子不讲武德，抢 4 个锁, 剩下的锁不够4个。下个无赖也需要4个锁，就只有等老子释放锁, 可惜老子只释放3个锁。");
        semaphore.release(3);
    }

}
