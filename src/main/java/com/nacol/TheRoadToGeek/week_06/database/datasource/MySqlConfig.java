package com.nacol.TheRoadToGeek.week_06.database.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "spring.datasource.mysql-config")
@Data
public class MySqlConfig extends DatabaseConfig{


}
