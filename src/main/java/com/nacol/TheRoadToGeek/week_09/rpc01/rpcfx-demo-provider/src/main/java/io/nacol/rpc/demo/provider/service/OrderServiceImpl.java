package io.nacol.rpc.demo.provider.service;

import io.nacol.rpc.demo.api.Order;
import io.nacol.rpc.demo.api.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
