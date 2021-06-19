package com.nacol.TheRoadToGeek.week_06.database.threadconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadConfig {

    @Bean("jdbc-fixedThreadPools")
    public ExecutorService fixedThreadPools() {
        return Executors.newFixedThreadPool(20);
    }

}
