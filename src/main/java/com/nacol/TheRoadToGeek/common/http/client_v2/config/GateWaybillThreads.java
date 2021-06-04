package com.nacol.TheRoadToGeek.common.http.client_v2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class GateWaybillThreads {

    public static final int CORE_NUM = Runtime.getRuntime().availableProcessors() + 1;

    @Bean(name = "HttpRequestExcutor")
    public ThreadPoolTaskExecutor requestExcutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("gateway-task-pool");
        executor.setCorePoolSize(CORE_NUM);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(30000);
        executor.setQueueCapacity(2000);
        executor.setMaxPoolSize(CORE_NUM * 2 - 1);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
