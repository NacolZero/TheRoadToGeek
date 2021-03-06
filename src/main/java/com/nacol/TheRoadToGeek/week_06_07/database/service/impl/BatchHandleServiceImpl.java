package com.nacol.TheRoadToGeek.week_06_07.database.service.impl;

import com.nacol.TheRoadToGeek.common.utils.DateUtils;
import com.nacol.TheRoadToGeek.common.utils.IdUtils;
import com.nacol.TheRoadToGeek.common.utils.SerialNoUtils;
import com.nacol.TheRoadToGeek.common.utils.SnowflakeIdWorker;
import com.nacol.TheRoadToGeek.week_06_07.database.entity.BatchDTO;
import com.nacol.TheRoadToGeek.week_06_07.database.service.BatchHandleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.nacol.TheRoadToGeek.week_06_07.database.datasource.base.DataSourceNameConfig.*;

@Log4j2
@Service
public class BatchHandleServiceImpl implements BatchHandleService {

    @Resource(name = "basicForMySQLDataSource")
    DataSource basicForMySQLDataSource;

    @Resource(name = "basicForPgSQLDataSource")
    DataSource basicForPgSQLDataSource;

    @Resource(name = HIKARI_PGSQL)
    DataSource hikariForPgSQLDataSource;

    @Resource(name = HIKARI_MYSQL)
    DataSource hikariForMySQLDataSource;

    @Resource(name = DRUID_PGSQL)
    DataSource druidForMySQLDataSource;

    @Resource(name = DRUID_MYSQL)
    DataSource druidForPgSQLDataSource;

    @Resource(name = "jdbc-fixedThreadPools")
    ExecutorService executorService;

    @Resource(name = "snowflakeFactory")
    SnowflakeIdWorker snowflakeFactory;

    private static final String SQL_INSERT_3 = "insert into order_base_2 (serial_no)  values (?)";

    private static final String SQL_INSERT_2 = "insert into order_base_2 (serial_no, create_time, update_time, user_id, " +
            "user_address_id, seller_id, cmd_base_id, cmd_entity_id, pay_type) " +
            "values (?,?,?,?,?,?,?,?,?)";

    private static final String SQL_INSERT_BASE_ODER = "insert into order_base (id, serial_no, create_time, update_time, user_id, " +
            "user_address_id, seller_id, cmd_base_id, cmd_entity_id, pay_type) " +
            "values (?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE = "update order_base set serial_no=?, update_time=?, user_id=?, " +
            "user_address_id=?, seller_id=?, cmd_base_id=?, cmd_entity_id=?, pay_type =? where id=?";

    private static final String SQL_SELECT = "select * from order_base where del_flag = 0";

    private static final String SQL_DELETE = "delete from order_base where id=?";

    private static final int MILLION = 30000000/20;

    private static final int THOUSAND = 1000;

    //2021-01-01 00:00:00
    private long START_TIME = 1609430400000L;
    //2022-01-01 00:00:00
    private long END_TIME = 1640966400000L;
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
            PreparedStatement insertStatement = conn.prepareStatement(SQL_INSERT_BASE_ODER);
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
                    + "insert ?????? " + (insertEndTime - insertStartTime) + " ??????"
                    + ", select ?????? " + (selectEndTime - insertEndTime) + " ??????"
                    + ", update ?????? " + (udpateEndTime - selectEndTime) + " ??????"
                    + ", delete ?????? " + (deleteEndTime - udpateEndTime) + " ??????.";
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
        System.out.println("?????????>>>>>>" + dataSource.getClass());
        System.out.println("??????>>>>>>>>" + conn);
        System.out.println("????????????>>>>" + conn.getMetaData().getURL());
        System.out.println("????????????>>>>" + metaData.getDriverName());
        System.out.println("????????????>>>>" + metaData.getDriverVersion());
        System.out.println("???????????????>>" + metaData.getDatabaseProductName());
        System.out.println("???????????????>>" + metaData.getDatabaseProductVersion());
    }

    @Override
    public String batchInsert(BatchDTO param) throws ExecutionException, InterruptedException {
        //STEP ????????????????????????
        Set<String> orderBaseIds = new HashSet<>();
        craeteOrderBase(orderBaseIds);
        return null;
    }

    private void craeteOrderBase(Set<String> orderBaseIds) throws ExecutionException, InterruptedException {
        long startTimee = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Future f = executorService.submit(() -> {
                try (Connection conn = hikariForMySQLDataSource.getConnection();
                     PreparedStatement insertStatement = conn.prepareStatement(SQL_INSERT_BASE_ODER)) {
                    conn.setAutoCommit(false);
                    //STEP INSERT
                    for (int j = 0; j < MILLION; j++) {
                        String orderBaseId = IdUtils.generateUUID();
                        orderBaseIds.add(orderBaseId);
                        insertStatement.setString(1, orderBaseId);
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
                        //?????????????????????????????????50???100???500???1000??????
                        if (j % 10000 == 0) {
                            insertStatement.executeBatch();
                            conn.commit();
                            insertStatement.clearBatch();
                        }
                    }
                    insertStatement.executeBatch();
                    System.out.println(Thread.currentThread().getName() + " ??????");
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
        System.out.println("use time : " + (System.currentTimeMillis() - startTimee));
    }
    private static final String SQL_INSERT_PAMENT = "insert into order_payment (id, cmd_entity_id, order_base_id, payment_type, user_bank_id, " +
            "alipy_id, amount, seller_id, create_time, update_time) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public String batchInsertPayment(BatchDTO param) throws ExecutionException, InterruptedException {
        long startTimee = System.currentTimeMillis();
        List<Future> futures = new ArrayList<>();
        List<String> cmdEntityId = new ArrayList<>();
        List<String> sellerIds = new ArrayList<>();

        for (int i = 0; i < THOUSAND; i++) {
            cmdEntityId.add(IdUtils.generateUUID());
            sellerIds.add(IdUtils.generateUUID());
        }
        for (int i = 0; i < 20; i++) {
            Future f = executorService.submit(() -> {
                try (Connection conn = hikariForMySQLDataSource.getConnection();
                     PreparedStatement insertStatement = conn.prepareStatement(SQL_INSERT_PAMENT)) {
                    conn.setAutoCommit(false);
                    //STEP INSERT
                    for (int j = 0; j < MILLION; j++) {
                        insertStatement.setString(1, IdUtils.generateUUID());
                        insertStatement.setString(2, cmdEntityId.get(new Random().nextInt(THOUSAND)));
                        insertStatement.setString(3, IdUtils.generateUUID());
                        insertStatement.setInt(4, new Random().nextInt(3));
                        insertStatement.setString(5, IdUtils.generateUUID());
                        insertStatement.setString(6, IdUtils.generateUUID());
                        insertStatement.setInt(7, new Random().nextInt(10000));
                        insertStatement.setString(8, sellerIds.get(new Random().nextInt(THOUSAND)));
                        insertStatement.setLong(9, new Random().nextInt((int)(END_TIME -  START_TIME)+ 1) + START_TIME);
                        insertStatement.setLong(10, DateUtils.getCurrentNano());
                        insertStatement.addBatch();
                        //?????????????????????????????????50???100???500???1000??????
                        if (j % 10000 == 0) {
                            insertStatement.executeBatch();
                            conn.commit();
                            insertStatement.clearBatch();
                        }
                    }
                    insertStatement.executeBatch();
                    System.out.println(Thread.currentThread().getName() + " ??????");
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
        System.out.println("use time : " + (System.currentTimeMillis() - startTimee));
        return null;
    }
}
