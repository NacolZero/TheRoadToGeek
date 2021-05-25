package com.nacol.TheRoadToGeek.week_04.temp;

import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/25
 * @Description 两个线程交替执行
 */
public class AlternateMain {

    public static void main(String[] args) {
        //小强小红互相唤醒
//        qiangAndHong();
        //无法顺序交互，因为只能控制并发量为1，新的线程需要他们去竞争这个信号量导致顺序不可控
//        runSemaphore();
    }

    private static void runSemaphore() {
        Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 10000; i++) {
            Worker w1 = new Worker(semaphore, "a");
            Worker w2 = new Worker(semaphore,"b");
            w1.start();
            w2.start();
        }
    }

    private static void qiangAndHong() {
        SmallHong hong = new SmallHong();
        SmallQiang qiang = new SmallQiang();
        hong.setQiang(qiang);
        qiang.setHong(hong);
        hong.start();
        qiang.start();
    }

}

class Worker extends Thread {

    private  Semaphore semaphore;

    private String name;

    public Worker(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        semaphore.acquire(1);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(this.name);
        semaphore.release(1);
    }

}




class SmallHong extends Thread {

    private SmallQiang qiang;

    @SneakyThrows
    @Override
    public void run() {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("小红开始唤醒小强");
        LockSupport.unpark(qiang);
        System.out.println("小红开始睡觉");
        LockSupport.park();
        System.out.println("小红被唤醒");

    }

    public void setQiang(SmallQiang qiang) {
        this.qiang = qiang;
    }
}

class SmallQiang extends Thread {

    SmallHong hong;

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("小强睡觉");
        LockSupport.park();
        System.out.println("小强睡觉被唤醒");
        TimeUnit.SECONDS.sleep(2);

        System.out.println("小强开始唤醒小红");
        LockSupport.unpark(hong);

    }

    public void setHong(SmallHong hong) {
        this.hong = hong;
    }

}