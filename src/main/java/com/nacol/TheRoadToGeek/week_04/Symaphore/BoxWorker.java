package com.nacol.TheRoadToGeek.week_04.Symaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BoxWorker {

    private Semaphore semaphore = new Semaphore(4);
    private int boxNum = 0;

    public void doWork() throws InterruptedException {
        semaphore.acquire();
        ++boxNum ;
        System.out.println("箱子工人正在搬运" + boxNum + "个箱子");
        TimeUnit.SECONDS.sleep(2);
        semaphore.release();
    }

}
