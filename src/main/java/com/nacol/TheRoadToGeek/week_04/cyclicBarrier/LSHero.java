package com.nacol.TheRoadToGeek.week_04.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class LSHero {

    private int sn;

    private CyclicBarrier barrier;

    public LSHero(int sn, CyclicBarrier barrier){
        this.sn = sn;
        this.barrier = barrier;
    }

    public void goMountain() throws BrokenBarrierException, InterruptedException {
        TimeUnit.SECONDS.sleep(new Random().nextInt(3));
        System.out.println("梁山好汉 ： " + sn + " 号上山");
        barrier.await();
        System.out.println(sn + "号好汉，下山起义！");
    }

}
