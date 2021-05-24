package com.nacol.TheRoadToGeek.week_04.LockSupport;

import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
        unpark();

    }

    private static void unpark() {
        Plane p1 = new Plane("1#");
        Plane p2 = new Plane("2#");
        Plane p3 = new Plane("3#");
        Plane p4 = new Plane("4#");
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        //可指定唤醒一个线程
        LockSupport.unpark(p3);
    }

}

class Plane extends Thread{

    private String sn;

    public Plane(String sn) {
        this.sn = sn;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(sn + " : fly....start");
        Thread.sleep(1000);
        LockSupport.park();
        System.out.println(sn + " : fly....end");
    }

}
