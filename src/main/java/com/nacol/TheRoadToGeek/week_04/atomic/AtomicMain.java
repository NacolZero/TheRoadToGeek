package com.nacol.TheRoadToGeek.week_04.atomic;

import java.util.concurrent.atomic.LongAdder;

public class AtomicMain {

    private static AtomicIntegerCounter atomictIntCounter = new AtomicIntegerCounter();
    private static LongAdderCounter longAdderCounter = new LongAdderCounter();

    public static void main(String[] args) throws InterruptedException {
//        runAtomicInteger();
//        playLongAdder();
        runLongAdder();
    }

    private static void playLongAdder() {
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        longAdder.decrement();
        System.out.println(longAdder.doubleValue());
        longAdder.reset();
        System.out.println(longAdder.longValue());
    }

    private static void runLongAdder() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread t1 = new Thread(()->{
                longAdderCounter.add();
            });
            t1.start();
        }
        Thread.sleep(1000);
        System.out.println(longAdderCounter.get());
    }

    private static void runAtomicInteger() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread t1 = new Thread(()->{
                atomictIntCounter.getAndIncrement();
            });
            t1.start();
        }
        Thread.sleep(1000);
        System.out.println(atomictIntCounter.get());
    }

}

