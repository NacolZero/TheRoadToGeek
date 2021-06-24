package com.nacol.TheRoadToGeek.week_06_07.database.datasource.base;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.nacol.TheRoadToGeek.week_06_07.database.datasource.base.DataSourceNameConfig.HIKARI_MYSQL;
import static com.nacol.TheRoadToGeek.week_06_07.database.datasource.base.DataSourceNameConfig.HIKARI_PGSQL;

@Configuration
public class HikariDataSourceConfig {

    @Autowired
    MySqlConfig mySqlConfig;

    @Autowired
    PostgreSqlConfig postgreSqlConfig;

//    @Primary
    @Bean(name = HIKARI_MYSQL)
    public HikariDataSource hikariForMySQLDataSource() {
        return initHikariDataSource(mySqlConfig);
    }

    @Bean(name = HIKARI_PGSQL)
    public HikariDataSource hikariForPgSQLDataSource() {
        return initHikariDataSource(postgreSqlConfig);
    }

    private HikariDataSource initHikariDataSource(DatabaseConfig databaseConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(databaseConfig.getUsername());
        dataSource.setPassword(databaseConfig.getPassword());
        dataSource.setJdbcUrl(databaseConfig.getJdbcUrl());
        dataSource.setDriverClassName(databaseConfig.getDriverClassName());
        dataSource.setMaximumPoolSize(databaseConfig.getMaximumPoolSize());
        return dataSource;
    }

}
