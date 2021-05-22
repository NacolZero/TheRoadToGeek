package com.nacol.TheRoadToGeek.week_04.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchMain {

    public static void main(String[] args) throws InterruptedException {
        //大哥带小弟找龙珠
//        dragonBall();
        //将军带兵找七龙珠（线程池）
        dragonBall2();
    }

    private static void dragonBall2() throws InterruptedException {
        final int ballNum = 7;
        CountDownLatch remainingDragonBalls = new CountDownLatch(ballNum);
        ExecutorService executorService = Executors.newFixedThreadPool(ballNum);
        System.out.println("将军：军队们开始找龙珠了！");
        for (int i = 0; i < ballNum; i++) {
            executorService.execute(()->{
                new Hunter(remainingDragonBalls).findDragonBall();
            });
        }
        System.out.println("将军：喝会茶等你们。");
        remainingDragonBalls.await();
        System.out.println("士兵们找齐了七龙珠");
        System.out.println("将军许愿变成了女人");

    }

    private static void dragonBall() throws InterruptedException {
        CountDownLatch remainingDragonBalls = new CountDownLatch(7);
        System.out.println("老大：小弟们开始找龙珠了！");
        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                new Hunter(remainingDragonBalls).findDragonBall();
            }).start();
        }
        System.out.println("老大：等你们哦");
        remainingDragonBalls.await();
        System.out.println("小弟们找齐了七龙珠");
        System.out.println("老大许愿要了一根白色内裤(男士)");
    }

}
