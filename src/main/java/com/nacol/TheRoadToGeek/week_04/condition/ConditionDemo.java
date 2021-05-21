package com.nacol.TheRoadToGeek.week_04.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    public static void main(String[] args) {
        Fater daMingGe = new Fater();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1 ; i++) {
            executor.submit(()->{
                try {
                    daMingGe.eat();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            executor.submit(()->{
                try {
                    daMingGe.shit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
//        for (int i = 0; i < 1000000000 ; i++) {
//            executor.submit(()->{
//
//            });
//        }
    }

}

class Fater {


    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void eat() throws InterruptedException {
        try {

            lock.lock();
            System.out.println("等着吃饭");
            condition.await();
            System.out.println("开始吃饭");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void shit() throws InterruptedException {
        try {
            lock.lock();
            condition.signalAll();
            System.out.println("叫大神吃饭了");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
