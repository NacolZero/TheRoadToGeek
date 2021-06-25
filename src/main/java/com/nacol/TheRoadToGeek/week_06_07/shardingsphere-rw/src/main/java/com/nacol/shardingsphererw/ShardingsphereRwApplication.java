package com.nacol.shardingsphererw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.nacol.shardingsphererw.mapper")
@SpringBootApplication
        (exclude= {DataSourceAutoConfiguration.class})
public class ShardingsphereRwApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereRwApplication.class, args);
    }

}
