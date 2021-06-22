package com.nacol.TheRoadToGeek.week_06.database.service;

import com.nacol.TheRoadToGeek.week_06.database.entity.BatchDTO;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface BatchHandleService {

    String batchHandle(BatchDTO param) throws SQLException;

    String batchTest(BatchDTO param) throws SQLException;

    String batchInsert(BatchDTO param) throws ExecutionException, InterruptedException;
}
