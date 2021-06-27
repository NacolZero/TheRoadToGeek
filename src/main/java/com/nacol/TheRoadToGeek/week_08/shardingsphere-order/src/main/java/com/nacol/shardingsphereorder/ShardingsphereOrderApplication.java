package com.nacol.shardingsphereorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nacol.shardingsphereorder.mapper")
@SpringBootApplication
public class ShardingsphereOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingsphereOrderApplication.class, args);
	}

}
