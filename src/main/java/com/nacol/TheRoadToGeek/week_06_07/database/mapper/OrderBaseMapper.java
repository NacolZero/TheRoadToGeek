package com.nacol.TheRoadToGeek.week_06_07.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nacol.TheRoadToGeek.week_06_07.database.entity.OrderBase;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBaseMapper extends BaseMapper<OrderBase> {

    public void insert();


}
