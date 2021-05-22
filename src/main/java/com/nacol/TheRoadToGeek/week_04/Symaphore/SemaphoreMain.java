package com.nacol.TheRoadToGeek.week_04.Symaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreMain {

    public static void main(String[] args) throws InterruptedException {
//        //箱子工人只能同时搬运4个箱子
//        boxRun();
//        //不讲武德，一次抢n个锁，释放n-1个锁，最终锁等于0，其他线程就傻逼一样的等。不讲武德。
//        noSayAboutMartialArts();
//        //测试的值，new Semaphore(n) 中的 n 并不是固定最大数量，仅仅是一个初始化值
//        //会随着 acquire 小于 n， 随着 release 大于 n
//        testRelease();
        BoxFactory factory = new BoxFactory();
        for (int i = 0; i < 1000; i++) {
            Thread producer = new Thread(()->{
                try {
                    factory.in();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            producer.start();
        }
        for (int i = 0; i < 1000; i++) {
            Thread consumer = new Thread(()->{
                try {
                    factory.out();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            consumer.start();
        }

    }

    private static void testRelease() throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
//        semaphore.acquire();
        System.out.println("1");
        semaphore.release(5);
        System.out.println("2");
        semaphore.acquire(2);
        System.out.println("3");
        semaphore.acquire(2);
        System.out.println("3");
    }

    private static void noSayAboutMartialArts() {
        Rascal rascal = new Rascal();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    rascal.doSemeThing();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void boxRun() {
        BoxWorker boxWorker = new BoxWorker();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    boxWorker.doWork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
