package com.nacol.TheRoadToGeek.week_03.task_6_gateway_pool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 其他地方调用，
 * 没用 Spring 不好注入，暂且这样初始化。
 */
public class GateWayThreadPool {

    private static ThreadPoolTaskExecutor executor = null;

    private static void init() {
        if (executor == null) {
            int coreNum = Runtime.getRuntime().availableProcessors() + 1;
            executor = new ThreadPoolTaskExecutor();
            executor.setThreadNamePrefix("gateway-task-pool");
            executor.setCorePoolSize(coreNum);
            executor.setAllowCoreThreadTimeOut(true);
            executor.setKeepAliveSeconds(30000);
            executor.setQueueCapacity(2000);
            executor.setMaxPoolSize(coreNum * 2 - 1);
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            executor.initialize();
        }
    }

    public static Future<?> submit(Runnable task) {
        init();
        return executor.submit(task);
    }



}
