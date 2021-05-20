package com.nacol.TheRoadToGeek.week_03.other.pool;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;

import java.util.concurrent.*;

public class FixPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            Future<?> result = pool.submit(()->new TaskHandler().doTask(new HttpRequestDto()));
            HttpResponseDto resp = (HttpResponseDto)result.get();
            System.out.println(resp.getStatus());
        }
    }
}
