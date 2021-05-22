package com.nacol.TheRoadToGeek.week_04.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Hunter {

    private CountDownLatch countDownLatch;

    public Hunter(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void findDragonBall(){
        System.out.println(Thread.currentThread().getName() + " : 猎人找到了龙珠");
        countDownLatch.countDown();
    }

}
