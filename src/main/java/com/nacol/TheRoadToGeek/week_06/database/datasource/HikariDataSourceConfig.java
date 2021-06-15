package com.nacol.TheRoadToGeek.week_06.database.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HikariDataSourceConfig {

    @Autowired
    MySqlConfig mySqlConfig;

    @Autowired
    PostgreSqlConfig postgreSqlConfig;

    @Primary
    @Bean(name = "hikariForMySQLDataSource")
    public HikariDataSource hikariForMySQLDataSource() {
        return initHikariDataSource(mySqlConfig);
    }

    @Bean(name = "hikariForPgSQLDataSource")
    public HikariDataSource hikariForPgSQLDataSource() {
        return initHikariDataSource(postgreSqlConfig);
    }

    private HikariDataSource initHikariDataSource(DatabaseConfig databaseConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(databaseConfig.getUsername());
        dataSource.setPassword(databaseConfig.getPassword());
        dataSource.setJdbcUrl(databaseConfig.getJdbcUrl());
        dataSource.setDriverClassName(databaseConfig.getDriverClassName());
        return dataSource;
    }

}
