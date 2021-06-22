package com.nacol.TheRoadToGeek.common.id;

import com.nacol.TheRoadToGeek.common.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfig {

    @Bean(name = "snowflakeFactory")
    public SnowflakeIdWorker snowflakeIdWorker() {
        SnowflakeIdWorker worker = new SnowflakeIdWorker(0, 0);
        return worker;
    }

}
