package com.nacol.TheRoadToGeek.week_04.atomic;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter {

    private LongAdder num = new LongAdder();

    public void add() {
        num.add(1);
    }

    public long get() {
        return num.longValue();
    }

}
