package com.nacol.shardingspheredeomo.service;

import com.nacol.shardingspheredeomo.entity.Order;
import com.nacol.shardingspheredeomo.entity.OrderItem;

import java.util.List;

public interface OrderService {

    int saveRecord(Order order, List<OrderItem> items);

}
