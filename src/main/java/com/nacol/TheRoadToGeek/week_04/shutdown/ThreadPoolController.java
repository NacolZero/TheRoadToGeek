package com.nacol.TheRoadToGeek.week_04.shutdown;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.*;

@Log4j2
@RestController
@RequestMapping("thread-pool")
public class ThreadPoolController {

    @Resource(name = "customPool")
    ThreadPoolExecutor customPool;
    @Resource(name = "fixedPool")
    ExecutorService fixedPool;
    @Resource(name = "cachedPool")
    ExecutorService cachedPool;
    @Resource(name = "schedulePool")
    ScheduledExecutorService schedulePool;

    @GetMapping("testThreadPool")
    public String testThreadPool() throws ExecutionException, InterruptedException {
        //自定义线程池
        /**
         * 经过测试给了 10000000 个任务后，
         * TaskCount 很快会到 10000000 → 也就是说接收的任务很快到 10000000 （历史总任务数量）
         * queue size 会跟随任务的完成速度和剩余量动态调整，虽然设置了 20000 的储量，但是随着任务的数量增多 size 会大得变态
         * ActiveCount 保持在 8~9
         * CompletedTaskCount ：历史完成数量
         */
        for (int i = 0; i < 10000000; i++) {
            //TODO callable 使用优先级的方式执行会报错，还未找到原因
//            Future<String> f = customPool.submit(new Baby(i));
//            System.out.println(f.get());
            //STEP runnable 可以用优先级执行
            customPool.execute(new Gaga(i));
        }
//
//        //固定大小线程池
//        for (int i = 0; i < 24; i++) {
//            Future<String> f = fixedPool.submit(new Baby(i));
//            System.out.println(f.get());
//        }
//
//        //缓存线程池
//        for (int i = 0; i < 10000; i++) {
//            Future<String> f = cachedPool.submit(new Baby(i));
//            System.out.println(f.get());
//        }
//
//        //周期调度线程池
//        for (int i = 0; i < 10; i++) {
//            Future<String> f = schedulePool.schedule(new Baby(i), 5, TimeUnit.SECONDS);
//            System.out.println(f.get());
//        }

        return "ok";
    }

    @GetMapping("/runtimeStatus")
    public JSONObject runtimeStatus() throws InterruptedException {

        JSONObject json = new JSONObject();
        json.put("queue size", customPool.getQueue().size());
        json.put("ActiveCount", customPool.getActiveCount());
        json.put("TaskCount", customPool.getTaskCount());
        json.put("CompletedTaskCount", customPool.getCompletedTaskCount());
        return json;
    }


    @GetMapping("/niceShutdown")
    public String niceShutdown() throws InterruptedException {
        schedulePool.shutdown();
        boolean status = schedulePool.awaitTermination(2, TimeUnit.SECONDS);
        if (status) {
            schedulePool.shutdownNow();
        }
        return "ok";
    }

    @GetMapping("/shutdown")
    public String shutdown() {
        schedulePool.shutdown();
        return "ok";
    }

    @GetMapping("/shutdownNow")
    public String shutdownNow() {
        schedulePool.shutdownNow();
        return "ok";
    }

    @GetMapping("/isShutDowun")
    public String isShutDowun() {
        System.out.println("isShutdown : " + schedulePool.isShutdown());
        return "ok";
    }


}

class Baby implements Callable, Comparable<Baby>{

    private int age;

    public Baby(int age) {
        this.age = age;
    }

    @Override
    public String call() throws InterruptedException {
        return Thread.currentThread().getName() + " : " + LocalDateTime.now() + " ------ " + age;
    }

    @Override
    public int compareTo(Baby otherBaby) {
        return compareTo(this, otherBaby);
    }

    public int compareTo(Baby me, Baby other) {
        return new CompareToBuilder().append(other.getAge(), me.getAge()).build();
    }

    public int getAge() {
        return this.age;
    }

}

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/21
 * @Description 该任务执行任务执行 "老人优先"
 */
class Gaga implements Runnable, Comparable<Gaga>{

    private int age;

    public Gaga(int age) {
        this.age = age;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + LocalDateTime.now() + " ------ " + age);
    }

    @Override
    public int compareTo(Gaga otherGaga) {
        return compareTo(this, otherGaga);
    }

    public int compareTo(Gaga me, Gaga other) {
        return new CompareToBuilder().append(other.getAge(), me.getAge()).build();
    }

    public int getAge() {
        return this.age;
    }
}
