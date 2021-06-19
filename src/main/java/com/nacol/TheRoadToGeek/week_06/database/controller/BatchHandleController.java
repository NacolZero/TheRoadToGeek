package com.nacol.TheRoadToGeek.week_06.database.controller;

import com.nacol.TheRoadToGeek.week_06.database.entity.BatchDTO;
import com.nacol.TheRoadToGeek.week_06.database.service.BatchHandleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("batch-handle")
@Log4j2
public class BatchHandleController {

    @Autowired
    BatchHandleService batchHandleService;

    @PostMapping("handle")
    public BatchDTO handle() throws SQLException {
        return batchHandleService.batchHandle();
}

}
