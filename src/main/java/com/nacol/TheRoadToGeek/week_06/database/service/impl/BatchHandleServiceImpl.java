package com.nacol.TheRoadToGeek.week_06.database.service.impl;

import com.nacol.TheRoadToGeek.common.utils.DateUtils;
import com.nacol.TheRoadToGeek.common.utils.IdUtils;
import com.nacol.TheRoadToGeek.common.utils.SerialNoUtils;
import com.nacol.TheRoadToGeek.week_06.database.entity.BatchDTO;
import com.nacol.TheRoadToGeek.week_06.database.service.BatchHandleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Log4j2
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

    @Resource(name = "jdbc-fixedThreadPools")
    ExecutorService executorService;

    private static final String SQL_INSERT_3 = "insert into order_base_2 (serial_no)  values (?)";

    private static final String SQL_INSERT_2 = "insert into order_base_2 (serial_no, create_time, update_time, user_id, " +
            "user_address_id, seller_id, cmd_base_id, cmd_entity_id, pay_type) " +
            "values (?,?,?,?,?,?,?,?,?)";

    private static final String SQL_INSERT = "insert into order_base (id, serial_no, create_time, update_time, user_id, " +
            "user_address_id, seller_id, cmd_base_id, cmd_entity_id, pay_type) " +
            "values (?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE = "update order_base set serial_no=?, update_time=?, user_id=?, " +
            "user_address_id=?, seller_id=?, cmd_base_id=?, cmd_entity_id=?, pay_type =? where id=?";

    private static final String SQL_SELECT = "select * from order_base where del_flag = 0";

    private static final String SQL_DELETE = "delete from order_base where id=?";

    private static final int MILLION = 10000000/20;

    @Override
    public String batchHandle(BatchDTO param) throws SQLException {
        String result = "";
        // basic
        if ("basicForMySQLDataSource".equals(param.getDataSourece())) {
            result = handle(basicForMySQLDataSource);
        } else if ("basicForPgSQLDataSource".equals(param.getDataSourece())) {
            result = handle(basicForPgSQLDataSource);
        }
        //hikari
        else if ("hikariForMySQLDataSource".equals(param.getDataSourece())) {
            result = handle(hikariForMySQLDataSource);
        } else if ("hikariForPgSQLDataSource".equals(param.getDataSourece())) {
            result = handle(hikariForPgSQLDataSource);
        }
        // druid
        else if ("druidForMySQLDataSource".equals(param.getDataSourece())) {
            result = handle(druidForMySQLDataSource);
        } else if ("druidForPgSQLDataSource".equals(param.getDataSourece())) {
            result = handle(druidForPgSQLDataSource);
        }
        return result;
    }

    @Override
    public String batchTest(BatchDTO param) throws SQLException {
        String result = "";
            result += handle(basicForMySQLDataSource);
            result += handle(basicForPgSQLDataSource);
            result += handle(hikariForMySQLDataSource);
            result += handle(hikariForPgSQLDataSource);
            result += handle(druidForMySQLDataSource);
            result += handle(druidForPgSQLDataSource);
        return result;
    }

    private String handle(DataSource dataSource) {
        String msg = "";
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
                insertStatement.addBatch();
            }
            log.info("inser is end");
            long insertEndTime = System.currentTimeMillis();

            //STEP SELECT
            ResultSet selectRs = selectStatement.executeQuery(SQL_SELECT);
            long selectEndTime = System.currentTimeMillis();
            log.info("select is end");

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
            log.info("udpate is end");
            //STEP DELETE
            for (String id : idSet) {
                deleteStatement.setString(1, id);
                deleteStatement.execute();
            }
            long deleteEndTime = System.currentTimeMillis();
            log.info("delete is end");

            msg = dataSoureceInfo + " : "
                    + "insert 用时 " + (insertEndTime - insertStartTime) + " 毫秒"
                    + ", select 用时 " + (selectEndTime - insertEndTime) + " 毫秒"
                    + ", update 用时 " + (udpateEndTime - selectEndTime) + " 毫秒"
                    + ", delete 用时 " + (deleteEndTime - udpateEndTime) + " 毫秒.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private String getDataSource(DataSource dataSource) throws SQLException {
        Connection conn = dataSource.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();
        return dataSource.getClass().getName() + " - " +  metaData.getDatabaseProductName();
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

    @Override
    public String batchInsert(BatchDTO param) throws ExecutionException, InterruptedException {
        long startTimee = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future f = executorService.submit(() -> {
                try (Connection conn = hikariForMySQLDataSource.getConnection();
                     PreparedStatement insertStatement = conn.prepareStatement(SQL_INSERT)) {
                    conn.setAutoCommit(false);
                    String dataSoureceInfo = getDataSource(hikariForMySQLDataSource);
                    //STEP INSERT
//                    private static final String SQL_INSERT_2 = "insert into order_base_2 (serial_no, create_time, update_time, user_id, " +
//                            "user_address_id, seller_id, cmd_base_id, cmd_entity_id, pay_type) " +
//                            "values (?,?,?,?,?,?,?,?,?)";
                    long insertStartTime = System.currentTimeMillis();
                    for (int j = 0; j < MILLION; j++) {
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

                        insertStatement.setInt(10, new Random().nextInt(2));
                        insertStatement.addBatch();
                        //可以设置不同的大小；如50，100，500，1000等等
                        if (j % 10000 == 0) {
                            insertStatement.executeBatch();
                            conn.commit();
                            insertStatement.clearBatch();
                        }
                    }
                    int[] counts = insertStatement.executeBatch();
//                    System.out.println("执行SQL次数 : " + counts.length + 1);
                    System.out.println(Thread.currentThread().getName() + " 完成");
                    conn.commit();
                    insertStatement.clearBatch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            futures.add(f);
        }
        for (Future future : futures) {
            future.get();
        }
        executorService.shutdown();
        System.out.println("use time : " + (System.currentTimeMillis() - startTimee));
        return null;
    }
}
