package com.nacol.TheRoadToGeek.week_03.other.pool;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<List<?>>> results = new ArrayList<>();
        List<Future<?>> results2 = new ArrayList<>();
        List<Future<?>> results3 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Future<?> r1 = pool.submit(()->new TaskHandler().doTask(new HttpRequestDto()));
            Future<List<?>> result = pool.submit(()->new TaskHandler().doMix(new HttpRequestDto()));
            Future<?> r2 = pool.submit(()->new TaskHandler().doTask(new HttpRequestDto()));
            results.add(result);
            results2.add(r1);
            results3.add(r2);
        }

        for (Future<?> future : results2) {
            HttpResponseDto rsp = (HttpResponseDto)future.get();
            System.out.println(rsp);
        }
        for (Future<?> result : results) {
            List<HttpResponseDto> resps = (List<HttpResponseDto>)result.get();
            for (HttpResponseDto resp : resps) {
                System.out.println(resp.getStatus());
            }
        }
        System.out.println("----------");
        for (Future<?> future : results3) {
            try {
                HttpResponseDto rsp = (HttpResponseDto)future.get();
                System.out.println(rsp);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
