package com.nacol.shardingspheredeomo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nacol.shardingspheredeomo.mapper")
@SpringBootApplication
public class ShardingSphereDeomoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereDeomoApplication.class, args);
    }

}
