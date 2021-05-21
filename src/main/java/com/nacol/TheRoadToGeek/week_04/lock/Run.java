package com.nacol.TheRoadToGeek.week_04.lock;

public class Run {


    public static void main(String[] args) {
//        runReentrant();
        runReadWrite();
    }

    public static void runReentrant() {
        NacolReentrantCounter counter = new NacolReentrantCounter();
        for (int i = 0; i < 100000; i++) {
            Thread t = new Thread(()->{
                System.out.println(counter.addAndGet());
            });
            t.start();
        }
    }

    public static void runReadWrite() {
        NacolReadWriteCounter counter = new NacolReadWriteCounter();
        for (int i = 0; i < 100000; i++) {
            Thread t1 = new Thread(()->{
                System.out.println("addAndGet : " + counter.addAndGet());
            });
            Thread t2 = new Thread(()->{
                System.out.println("getNum : " + counter.getNum());
            });
            t1.start();
            t2.start();
        }
    }
}
