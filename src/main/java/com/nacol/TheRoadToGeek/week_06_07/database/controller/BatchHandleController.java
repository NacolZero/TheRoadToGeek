package com.nacol.TheRoadToGeek.week_06_07.database.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nacol.TheRoadToGeek.week_06_07.database.entity.BatchDTO;
import com.nacol.TheRoadToGeek.week_06_07.database.entity.OrderBase;
import com.nacol.TheRoadToGeek.week_06_07.database.mapper.OrderBaseMapper;
import com.nacol.TheRoadToGeek.week_06_07.database.service.BatchHandleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("batch-handle")
@Log4j2
public class BatchHandleController {

    @Autowired
    BatchHandleService batchHandleService;
    @Autowired
    OrderBaseMapper orderBaseMapper;

    @PostMapping("handle")
    public String handle(@RequestBody BatchDTO param) throws SQLException {
        return batchHandleService.batchHandle(param);
    }

    @PostMapping("batchTest")
    public String batchTest(@RequestBody BatchDTO param) throws SQLException {
        return batchHandleService.batchTest(param);
    }


    @PostMapping("batchInsert")
    public String batchInsert(@RequestBody BatchDTO param) throws SQLException, ExecutionException, InterruptedException {
        return batchHandleService.batchInsert(param);
    }

    @PostMapping("batchInsertPayment")
    public String batchInsertPayment(@RequestBody BatchDTO param) throws SQLException, ExecutionException, InterruptedException {
        return batchHandleService.batchInsertPayment(param);
    }

    @PostMapping("test1")
    public String test1(){
        System.out.println(orderBaseMapper.selectList(new QueryWrapper<OrderBase>().eq("del_flag", 0)));
        return null;
    }
}
