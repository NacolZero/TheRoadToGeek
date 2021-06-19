package com.nacol.TheRoadToGeek.week_06.database.service.impl;

import com.nacol.TheRoadToGeek.common.utils.DateUtils;
import com.nacol.TheRoadToGeek.common.utils.IdUtils;
import com.nacol.TheRoadToGeek.common.utils.SerialNoUtils;
import com.nacol.TheRoadToGeek.week_06.database.entity.BatchDTO;
import com.nacol.TheRoadToGeek.week_06.database.service.BatchHandleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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


    private static final String SQL_INSERT = "insert into order_base (id, serial_no, create_time, update_time, user_id, " +
            "user_address_id, seller_id, cmd_base_id, cmd_entity_id, pay_type) " +
            "values (?,?,?,?,?,?,?,?,?,?);";

    private static final String SQL_UPDATE = "update order_base set serial_no=?, update_time=?, user_id=?, " +
            "user_address_id=?, seller_id=?, cmd_base_id=?, cmd_entity_id=?, pay_type =? where id=?";

    private static final String SQL_SELECT = "select * from order_base where del_flag = 0";

    private static final String SQL_DELETE = "delete from order_base where id=?";

    private static final int MILLION = 10000
            ;

    @Override
    public BatchDTO batchHandle() throws SQLException {
        System.out.println(handle(basicForMySQLDataSource));
        System.out.println(handle(basicForPgSQLDataSource));
        System.out.println(handle(hikariForPgSQLDataSource));
        System.out.println(handle(hikariForMySQLDataSource));
        System.out.println(handle(druidForMySQLDataSource));
        System.out.println(handle(druidForPgSQLDataSource));
        return null;
    }

    private BatchDTO handle(DataSource dataSource) {
        BatchDTO result = new BatchDTO();
        try(Connection conn = dataSource.getConnection();
            PreparedStatement insertStatement = conn.prepareStatement(SQL_INSERT);
            PreparedStatement updateStatement = conn.prepareStatement(SQL_UPDATE);
            Statement selectStatement = conn.createStatement();
            PreparedStatement deleteStatement = conn.prepareStatement(SQL_DELETE)) {

            String dataSoureceInfo = getDataSource(dataSource);
            //STEP INSERT
            long insertStartTime = System.currentTimeMillis();
            for (int i = 0; i < MILLION; i++) {
                //id
                insertStatement.setString(1, IdUtils.generateUUID());
                //serialNo
                insertStatement.setString(2, SerialNoUtils.generateSimpleSerialNo());
                //createTime
                insertStatement.setLong(3, DateUtils.getCurrentNano());
                //updateTime
                insertStatement.setLong(4, DateUtils.getCurrentNano());
                //userId
                insertStatement.setString(5, IdUtils.generateUUID());
                //userAddressId
                insertStatement.setString(6, IdUtils.generateUUID());
                //sellerId
                insertStatement.setString(7, IdUtils.generateUUID());
                //cmdBaseId
                insertStatement.setString(8, IdUtils.generateUUID());
                //cmdEntityId
                insertStatement.setString(9, IdUtils.generateUUID());
                //payType
                insertStatement.setInt(10, new Random().nextInt(2));
                insertStatement.execute();
            }
            System.out.println("inser is end");
            long insertEndTime = System.currentTimeMillis();

            //STEP SELECT
            ResultSet selectRs = selectStatement.executeQuery(SQL_SELECT);
            long selectEndTime = System.currentTimeMillis();
            System.out.println("select is end");

            Set<String> idSet = new HashSet<>();
            //STEP UPDATE
            while (selectRs.next()) {
                String id = selectRs.getString(1);
                idSet.add(id);
                updateStatement.setString(1, IdUtils.generateUUID());
                updateStatement.setLong(2, DateUtils.getCurrentNano());
                updateStatement.setString(3, IdUtils.generateUUID());
                updateStatement.setString(4, IdUtils.generateUUID());
                updateStatement.setString(5, IdUtils.generateUUID());
                updateStatement.setString(6, IdUtils.generateUUID());
                updateStatement.setString(7, IdUtils.generateUUID());
                updateStatement.setInt(8, new Random().nextInt(2));
                updateStatement.setString(9, id);
                updateStatement.execute();
            }
            long udpateEndTime = System.currentTimeMillis();
            System.out.println("udpate is end");
            //STEP DELETE
            for (String id : idSet) {
                deleteStatement.setString(1, id);
                deleteStatement.execute();
            }
            long deleteEndTime = System.currentTimeMillis();
            System.out.println("del is end");

            String msg = dataSoureceInfo + " : "
                    + "insert 用时 " + (insertEndTime - insertStartTime)
                    + ", select 用时 " + (selectEndTime - insertEndTime)
                    + ", update 用时 " + (udpateEndTime - selectEndTime)
                    + ", delete 用时 " + (deleteEndTime - udpateEndTime) + " .";
            result.setMessage(msg);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getDataSource(DataSource dataSource) throws SQLException {
        Connection conn = dataSource.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        return dataSource.getClass().getName() + " : " +  metaData.getDatabaseProductName();
    }

    private void printDataSource() throws SQLException {
        printDataSourceInfo(basicForMySQLDataSource);
        printDataSourceInfo(basicForPgSQLDataSource);
        printDataSourceInfo(hikariForPgSQLDataSource);
        printDataSourceInfo(hikariForMySQLDataSource);
        printDataSourceInfo(druidForMySQLDataSource);
        printDataSourceInfo(druidForPgSQLDataSource);
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
    }


}
