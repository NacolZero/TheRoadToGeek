package com.nacol.TheRoadToGeek;

import com.nacol.TheRoadToGeek.week_06_07.database.datasource.dynamic.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.nacol.TheRoadToGeek.week_06_07.database.mapper"})
@Import({DynamicDataSourceConfig.class})
public class TheRoadToGeekApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheRoadToGeekApplication.class, args);
	}

}
