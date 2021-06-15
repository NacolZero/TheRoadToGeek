package com.nacol.TheRoadToGeek.week_06.database.service.impl;

import com.nacol.TheRoadToGeek.week_06.database.entity.BatchDTO;
import com.nacol.TheRoadToGeek.week_06.database.service.BatchHandleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@Service
public class BatchHandleServiceImpl implements BatchHandleService {

    @Resource(name = "basicForMySQLDataSource")
    DataSource basicForMySQLDataSource;

    @Resource(name = "basicForPgSQLDataSource")
    DataSource basicForPgSQLDataSource;

    @Resource(name = "hikariForPgSQLDataSource")
    DataSource hikariForPgSQLDataSource;

    @Resource(name = "hikariForMySQLDataSource")
    DataSource hikariForMySQLDataSource;

    @Resource(name = "druidForMySQLDataSource")
    DataSource druidForMySQLDataSource;

    @Resource(name = "druidForPgSQLDataSource")
    DataSource druidForPgSQLDataSource;


    @Override
    public BatchDTO batchHandle() throws SQLException {
        printDataSourceInfo(basicForMySQLDataSource);
        printDataSourceInfo(basicForPgSQLDataSource);
        printDataSourceInfo(hikariForPgSQLDataSource);
        printDataSourceInfo(hikariForMySQLDataSource);
        printDataSourceInfo(druidForMySQLDataSource);
        printDataSourceInfo(druidForPgSQLDataSource);
        return null;
    }

    void printDataSourceInfo(DataSource dataSource) throws SQLException {
        Connection conn = dataSource.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();

        System.out.println("数据源>>>>>>" + dataSource.getClass());
        System.out.println("连接>>>>>>>>" + conn);
        System.out.println("连接地址>>>>" + conn.getMetaData().getURL());
        System.out.println("驱动名称>>>>" + metaData.getDriverName());
        System.out.println("驱动版本>>>>" + metaData.getDriverVersion());
        System.out.println("数据库名称>>" + metaData.getDatabaseProductName());
        System.out.println("数据库版本>>" + metaData.getDatabaseProductVersion());
        System.out.println("------------------------------------------------------");
    }

}
