package com.nacol.TheRoadToGeek.week_03.other.thread;

public class NacolRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("现在的线程是： " + Thread.currentThread());
    }

}
