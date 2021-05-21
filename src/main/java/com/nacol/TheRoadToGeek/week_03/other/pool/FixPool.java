package com.nacol.TheRoadToGeek.week_03.other.pool;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.other.callable.MixTaskHandlerPlus;
import com.nacol.TheRoadToGeek.week_03.other.callable.TaskHandlerPlus;
import com.nacol.TheRoadToGeek.week_03.other.woker.TaskHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testFutureThrowException();
        callableReturnType();
    }

    public static void testFutureThrowException() throws ExecutionException, InterruptedException{
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

        //  第 1 个 future.get() 正常
        for (Future<?> future : results2) {
            HttpResponseDto rsp = (HttpResponseDto)future.get();
            System.out.println(rsp);
        }
        //  第 2 个 future.get() 抛错
        for (Future<?> result : results) {
            List<HttpResponseDto> resps = (List<HttpResponseDto>)result.get();
            for (HttpResponseDto resp : resps) {
                System.out.println(resp.getStatus());
            }
        }
        System.out.println("----------");
        //  第 3 个 future.get() 因为主线程抛错了，则无法执行
        for (Future<?> future : results3) {
            try {
                HttpResponseDto rsp = (HttpResponseDto)future.get();
                System.out.println(rsp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void callableReturnType() throws ExecutionException, InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<HttpResponseDto>> result1 = new ArrayList<>();
        List<Future<List<HttpResponseDto>>> results = new ArrayList<>();
        List<Future<HttpResponseDto>> result2 = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Future<HttpResponseDto> r1 = pool.submit(new TaskHandlerPlus(new HttpRequestDto()));
            Future<List<HttpResponseDto>> result = pool.submit(new MixTaskHandlerPlus(new HttpRequestDto()));
            Future<HttpResponseDto> r2 = pool.submit(new TaskHandlerPlus(new HttpRequestDto()));
            results.add(result);
            result1.add(r1);
            result2.add(r2);
        }

        for (Future<HttpResponseDto> f : result1) {
            HttpResponseDto r = f.get();
            System.out.println(r);
        }

        for (Future<List<HttpResponseDto>> result : results) {
            List<HttpResponseDto> list = result.get();
            list.forEach(System.out::print);
        }

        for (Future<HttpResponseDto> f : result2) {
            HttpResponseDto r = f.get();
            System.out.println(r);
        }
    }
}
