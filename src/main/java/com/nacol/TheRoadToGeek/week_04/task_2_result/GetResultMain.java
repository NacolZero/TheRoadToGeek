package com.nacol.TheRoadToGeek.week_04.task_2_result;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class GetResultMain {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 有些方法本质上一直，写法上不同，不一一列举了
         */



//        CompletableFuture<Void> end = audience.thenAccept(it -> {
//            System.out.println("切菜:" + it);
//        });
//
//        end.join();

//        //线程池 + Callable
//        function01();
//
//        //线程池 + runnable + 实体类接收参数 + CountDownLatch 控制
//        function02();
//
//        //线程池(匿名内部类) + 强转类型
//        function03();
//
//        //FutureTask + Thread
//        function04();
//
//        //万物皆可 “睡眠疗法”
//        function05();
//
//        //主流程等子流程 join 过来
//        function06();
//
//        //CyclicBarrier → 回调 → 主线程等待回调线程 join 进来
//        function07();
//
//        // CompletableFuture
//        function08();
    }

    public static void function01() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        Future<String> result = pool.submit(new Worker());
        System.out.println(result.get());
    }

    public static void function02() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        ResultDto result = new ResultDto();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        pool.submit(new WorkerResult(result, countDownLatch));
        countDownLatch.await();
        System.out.println(result.getMsg());
    }

    public static void function03() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        Future<?> result = pool.submit(()->"OK");
        String resultStr = (String)result.get();
        System.out.println(resultStr);
    }

    public static void function04() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Worker());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    private static void function05() throws InterruptedException {
        ResultDto result = new ResultDto();
        WorkerResult worker = new WorkerResult(result);
        new Thread(worker).start();
        Thread.sleep(1000);
        System.out.println(result.getMsg());
    }

    private static void function06() throws InterruptedException {
        ResultDto result = new ResultDto();
        WorkerResult worker = new WorkerResult(result);
        Thread thread = new Thread(worker);
        thread.start();
        thread.join();
        System.out.println(result.getMsg());
    }

    public static void function07() throws InterruptedException {
        ResultDto result = new ResultDto();
        WorkerResult worker = new WorkerResult(result);
        Thread callBackMain = new Thread(worker);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, callBackMain);
        new WorkerResult(result, cyclicBarrier).run();
        callBackMain.join();
        System.out.println(result.getMsg());
    }

    private static void function08() throws ExecutionException, InterruptedException {
        CompletableFuture<String> person = CompletableFuture.supplyAsync(() -> {
            System.out.println("打工打工，做功做功");
            return "获取30K工资";
        });
        System.out.println(person.get());
    }

}


class Worker implements Callable<String> {

    @Override
    public String call() {
        return "OK";
    }

}

class WorkerResult implements Runnable {

    ResultDto result;
    CountDownLatch countDownLatch;
    CyclicBarrier cyclicBarrier;

    public WorkerResult(ResultDto result)  {
        this.result = result;
    }

    public WorkerResult(ResultDto result, CountDownLatch countDownLatch)  {
        this.result = result;
        this.countDownLatch = countDownLatch;
    }

    public WorkerResult(ResultDto result, CyclicBarrier cyclicBarrier)  {
        this.result = result;
        this.cyclicBarrier = cyclicBarrier;
    }

    public WorkerResult(ResultDto result, CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier)  {
        this.result = result;
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
    }

    @SneakyThrows
    @Override
    public void run() {
        result.setMsg("OK");

        if (countDownLatch != null) {
            countDownLatch.countDown();
        }

        if (cyclicBarrier != null) {
            cyclicBarrier.await();
        }
    }

}

class ResultDto {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}