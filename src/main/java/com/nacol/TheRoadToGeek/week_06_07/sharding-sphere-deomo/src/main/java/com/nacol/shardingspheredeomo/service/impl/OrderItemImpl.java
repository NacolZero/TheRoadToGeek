package com.nacol.shardingspheredeomo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nacol.shardingspheredeomo.entity.OrderItem;
import com.nacol.shardingspheredeomo.mapper.OrderItemMapper;
import com.nacol.shardingspheredeomo.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
