package com.nacol.TheRoadToGeek.week_03.task_5_multithreading.callable;

import java.util.concurrent.Callable;

public class Woker implements Callable<String> {



    @Override
    public String call() throws Exception {
        return "工具人！ 加油！";
    }
}
