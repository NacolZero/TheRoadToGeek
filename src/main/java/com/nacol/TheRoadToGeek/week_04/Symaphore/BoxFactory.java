package com.nacol.TheRoadToGeek.week_04.Symaphore;

import java.util.concurrent.Semaphore;

public class BoxFactory {

    Semaphore maxLcok = new Semaphore(5);
    Semaphore minLock = new Semaphore(0);
    Semaphore mutext = new Semaphore(1);
    int count = 0;


    public void in() throws InterruptedException {
        maxLcok.acquire();
        mutext.acquire();
        try {
            if (count < 5) {
                ++count;
                System.out.println("in num after: " + count);
            } else {
                System.out.println("in num : false");
            }
        } finally {
            mutext.release();
//            maxLcok.acquire();
        }
    }

    public void out() throws InterruptedException {
//        minLock.acquire();
        mutext.acquire();
        try {
            if (count > 0) {
                --count;
                System.out.println("out num after: " + count);
            } else {
                System.out.println("out num : false");
            }
        } finally {
            mutext.release();
//            minLock.release();
        }
    }


}
