package com.nacol.TheRoadToGeek.week_04.wait_notify;

import lombok.SneakyThrows;

public class JoinAndWait {

    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        GagaThread gagaThread = new GagaThread(obj, "gaga gaga ");
        gagaThread.start();
        gagaThread.join();
        synchronized (obj) {
            System.out.println("main get lock");
            for (int i = 0; i < 100; i++) {
                if (i == 50) {
//                    obj.wait(5000);
//                    Thread.sleep(10);
                    gagaThread.join();
                }
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        }
    }

    //case 1 :
    //  main.wait(5000),
    //  gaga.run 执行完毕
    //  5000 毫秒后 main 被唤醒继续 run

    //case 2 :
    //  main.wait(5000),
    //  gaga.run 到中途对 obj 执行 notifyAll()
    //  main 可能被立即唤醒 run，或者5000 毫秒后 main 被唤醒继续 run。

}

class GagaThread extends Thread{

    private Object obj;

    private String name;

    public GagaThread(Object obj, String name) {
        this.obj = obj;
        this.name = name;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("gaga run");
        //        Thread.sleep(500);
        //        System.out.println("finish sleep");
        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                //                if (i == 66) {
                //                    obj.notifyAll();
                //                }
                System.out.println(name + " - " + Thread.currentThread().getName() + " : " + i);
            }
        }
    }

}
