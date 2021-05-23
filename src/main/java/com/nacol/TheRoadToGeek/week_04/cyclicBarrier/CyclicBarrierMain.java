package com.nacol.TheRoadToGeek.week_04.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierMain {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        shuiHu();
    }

    private static void shuiHu() throws InterruptedException {
        final int num = 108;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num, new Thread(() -> {
            System.out.println("-------------------------------------全部上山集合完毕，准备下山起义！");
        }));
        for (int i = 1; i <= 108; i++) {
            int sn = i;
            new Thread(() -> {
                try {
                    new LSHero(sn, cyclicBarrier).goMountain();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(10000);
        System.out.println("---------------穿越回去，重来一次");
        //cyclicBarrier.reset() 通过 reset 可复用
        cyclicBarrier.reset();

        for (int i = 1; i <= 108; i++) {
            int sn = i;
            new Thread(() -> {
                try {
                    new LSHero(sn, cyclicBarrier).goMountain();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
