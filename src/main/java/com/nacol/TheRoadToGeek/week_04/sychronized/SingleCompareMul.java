package com.nacol.TheRoadToGeek.week_04.sychronized;

public class SingleCompareMul {

    public static void main(String[] args) throws InterruptedException {
        int loop = 10000;
        Counter c1 = new Counter();
        for (int i = 0; i < loop; i++) {
            c1.add();
        }
        System.out.println(c1.getNum());

        Counter c2 = new Counter();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < loop; i++) {
                c2.add();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < loop; i++) {
                c2.add();
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(c2.getNum());

    }


}

class Counter {

    Integer lock = new Integer(10000);//

    volatile int num;

    int getNum() {
            return this.num;
    }

//    synchronized void add() {
    void add() {
        synchronized (this) {
//        synchronized (lock) {
            num++;
        }
        //STEP 1: 取 num
        //STEP 2: 取 num
        //STEP 3: +
        //STEP 4: 赋值
    }

    //case1 锁方法
    //case2 锁代码块(this)
    //case3 锁其他对象
}
