package com.nacol.TheRoadToGeek.week_04.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerCounter {

    private AtomicInteger num = new AtomicInteger();

    public int getAndIncrement() {
        return num.getAndIncrement();
    }

    public int get() {
        return num.get();
    }

}
