package com.nacol.TheRoadToGeek.week_04.op;

public class ProAnCus {

    public static void main(String[] args) {
        Factory factory = new Factory();

        Thread proThread = new Thread(()-> {
            try {
                factory.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread cusThread = new Thread(()-> {
            try {
                factory.custom();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        proThread.start();
        cusThread.start();
    }
}

class Factory {

    final int max = 20;
    int currentNum = 0;

    public synchronized void produce() throws InterruptedException {
        while (true) {
            Thread.sleep(10);
            if (currentNum == 20) {
                System.out.println("多了我停" + currentNum);
                wait(1);
            } else {
                System.out.println("+++++++++++++" + currentNum);
                currentNum++;
            }

        }
    }

    public synchronized void custom() throws InterruptedException {
        while (true) {
            Thread.sleep(10);
            if (currentNum == 0) {
                System.out.println("没了我等" + currentNum);
                wait(1);
            } else {
                currentNum--;
                System.out.println("--------------" + currentNum);
            }

        }

    }

}
