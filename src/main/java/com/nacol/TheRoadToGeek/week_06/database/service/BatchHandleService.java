package com.nacol.TheRoadToGeek.week_06.database.service;

import com.nacol.TheRoadToGeek.week_06.database.entity.BatchDTO;

import java.sql.SQLException;

public interface BatchHandleService {

    BatchDTO batchHandle() throws SQLException;

}
