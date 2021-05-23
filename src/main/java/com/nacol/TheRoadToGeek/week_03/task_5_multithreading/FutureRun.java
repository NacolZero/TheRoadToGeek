package com.nacol.TheRoadToGeek.week_03.task_5_multithreading;

import com.nacol.TheRoadToGeek.week_03.task_5_multithreading.callable.Woker;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureRun {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Woker woker =  new Woker();
        FutureTask<String> future = new FutureTask<>(woker);
        new Thread(future).start();
        System.out.println(future.get());
    }


}
