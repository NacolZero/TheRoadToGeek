package com.nacol.shardingspheredeomo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nacol.shardingspheredeomo.entity.Order;
import com.nacol.shardingspheredeomo.entity.OrderItem;
import com.nacol.shardingspheredeomo.mapper.OrderMapper;
import com.nacol.shardingspheredeomo.service.OrderItemService;
import com.nacol.shardingspheredeomo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderItemService orderItemService;

    @Override
    public int saveRecord(Order order, List<OrderItem> items) {
        this.save(order);
        orderItemService.saveBatch(items);
        return 0;
    }
}
