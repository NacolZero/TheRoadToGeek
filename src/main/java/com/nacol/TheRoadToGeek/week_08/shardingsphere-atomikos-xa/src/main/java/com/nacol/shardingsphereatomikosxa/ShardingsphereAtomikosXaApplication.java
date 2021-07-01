package com.nacol.shardingsphereatomikosxa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nacol.shardingsphereatomikosxa.mapper")
@SpringBootApplication
public class ShardingsphereAtomikosXaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingsphereAtomikosXaApplication.class, args);
	}

}
