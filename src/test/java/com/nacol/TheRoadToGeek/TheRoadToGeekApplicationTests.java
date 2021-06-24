package com.nacol.TheRoadToGeek;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nacol.TheRoadToGeek.week_06_07.database.entity.OrderBase;
import com.nacol.TheRoadToGeek.week_06_07.database.mapper.OrderBaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TheRoadToGeekApplicationTests {

	@Autowired
	OrderBaseMapper orderBaseMapper;

	@Test
	void contextLoads() {
		System.out.println(orderBaseMapper.selectList(new QueryWrapper<OrderBase>().gt("create_time", 0)));
	}

}
