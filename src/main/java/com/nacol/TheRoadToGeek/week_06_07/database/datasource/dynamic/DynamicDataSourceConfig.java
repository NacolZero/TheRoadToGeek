package com.nacol.TheRoadToGeek.week_06_07.database.datasource.dynamic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

import static com.nacol.TheRoadToGeek.week_06_07.database.datasource.base.DataSourceNameConfig.*;

@Configuration
@DependsOn(value = {HIKARI_MYSQL, HIKARI_PGSQL, DRUID_MYSQL, DRUID_PGSQL})
public class DynamicDataSourceConfig {

    @Resource(name = HIKARI_MYSQL)
    DataSource hikariMySQL;

    @Resource(name = HIKARI_PGSQL)
    DataSource hikariPgSQL;

    @Resource(name = DRUID_MYSQL)
    DataSource druidMySQL;

    @Resource(name = DRUID_PGSQL)
    DataSource druidPgSQL;

    @Bean
    @Primary
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(HIKARI_MYSQL, hikariMySQL);
        targetDataSources.put(HIKARI_PGSQL, hikariPgSQL);
        targetDataSources.put(DRUID_MYSQL, druidMySQL);
        targetDataSources.put(DRUID_PGSQL, druidPgSQL);
        return new DynamicDataSource(hikariMySQL, targetDataSources);
    }

}
