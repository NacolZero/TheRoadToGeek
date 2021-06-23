package com.nacol.TheRoadToGeek.week_06_07.database.service;

import com.nacol.TheRoadToGeek.week_06_07.database.entity.BatchDTO;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface BatchHandleService {

    String batchHandle(BatchDTO param) throws SQLException;

    String batchTest(BatchDTO param) throws SQLException;

    String batchInsert(BatchDTO param) throws ExecutionException, InterruptedException;

    String batchInsertPayment(BatchDTO param) throws ExecutionException, InterruptedException;
}
