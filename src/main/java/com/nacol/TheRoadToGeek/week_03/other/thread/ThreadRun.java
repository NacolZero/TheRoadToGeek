package com.nacol.TheRoadToGeek.week_03.other.thread;

public class ThreadRun {

    public static void main(String[] args) throws InterruptedException {
        Runnable task = ()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程为：" + Thread.currentThread() + ", 活跃线程： " + Thread.activeCount());
        };

        Thread t = new Thread(task);
        t.setName("nacol-thread-1");
        t.setDaemon(true);
        t.start();
        Thread.sleep(2001);

        new NacolRunnable().run();
    }


}
