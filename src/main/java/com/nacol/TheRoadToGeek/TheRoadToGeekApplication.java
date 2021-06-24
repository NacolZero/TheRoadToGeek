package com.nacol.TheRoadToGeek;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.nacol.TheRoadToGeek.week_06_07.database.mapper"})
public class TheRoadToGeekApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheRoadToGeekApplication.class, args);
	}

}
