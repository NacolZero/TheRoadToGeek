package com.nacol.TheRoadToGeek.week_06_07.database.datasource.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.pgsql-config")
public class PostgreSqlConfig extends DatabaseConfig {

}
