package com.nacol.TheRoadToGeek.week_06_07.database.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DruidDataSourceConfig {
    @Autowired
    MySqlConfig mySqlConfig;

    @Autowired
    PostgreSqlConfig postgreSqlConfig;

    @Bean(name = "druidForMySQLDataSource")
    public DruidDataSource druidForMySQLDataSource() {
        return initDruidDataSource(mySqlConfig);
    }


    @Bean(name = "druidForPgSQLDataSource")
    public DruidDataSource druidForPgSQLDataSource() {
        return initDruidDataSource(postgreSqlConfig);
    }

    private DruidDataSource initDruidDataSource(DatabaseConfig databaseConfig) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(databaseConfig.getUsername());
        dataSource.setPassword(databaseConfig.getPassword());
        dataSource.setUrl(databaseConfig.getJdbcUrl());
        dataSource.setDriverClassName(databaseConfig.getDriverClassName());
        dataSource.setMaxActive(databaseConfig.getMaximumPoolSize());
        return dataSource;
    }
}
