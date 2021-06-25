package com.nacol.shardingspheredeomo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nacol.shardingspheredeomo.entity.Order;
import com.nacol.shardingspheredeomo.entity.OrderItem;
import com.nacol.shardingspheredeomo.mapper.OrderMapper;
import com.nacol.shardingspheredeomo.service.OrderItemService;
import com.nacol.shardingspheredeomo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ShardingSphereDeomoApplicationTests {

    @Autowired
    private OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Test
    public void testSave(){
//        for (int i = 0; i < 100; i++) {
//            Order order1=new Order((long)i,(long)i, "1");
//            orderMapper.insert(order1);
//        }
        Order order1=new Order(1L,2L, "1");
        List<OrderItem> orderItemList1=new ArrayList<OrderItem>();
        orderItemList1.add(new OrderItem(1L,1L,1L,"苹果"));
        orderItemList1.add(new OrderItem(2L,1L,2L,"香蕉"));

        Order order2=new Order(2L,2L,"2");
        List<OrderItem> orderItemList2=new ArrayList<OrderItem>();
        orderItemList2.add(new OrderItem(3L,2L,3L,"鞋子1"));
        orderItemList2.add(new OrderItem(4L,2L,4L,"衬衣1"));

        Order order3=new Order(3L,1L,"3");
        List<OrderItem> orderItemList3=new ArrayList<OrderItem>();
        orderItemList3.add(new OrderItem(5L,3L,5L,"鞋子2"));
        orderItemList3.add(new OrderItem(6L,3L,6L,"衬衣2"));

        Order order4=new Order(4L,1L,"4");
        List<OrderItem> orderItemList4=new ArrayList<OrderItem>();
        orderItemList4.add(new OrderItem(7L,4L,7L,"鞋子3"));
        orderItemList4.add(new OrderItem(8L,4L,8L,"衬衣3"));

        Order order5=new Order(5L,1L,"5");
        List<OrderItem> orderItemList5=new ArrayList<OrderItem>();
        orderItemList5.add(new OrderItem(9L,5L,9L,"鞋子4"));
        orderItemList5.add(new OrderItem(10L,5L,10L,"衬衣4"));
        orderService.saveRecord(order1,orderItemList1);
        orderService.saveRecord(order2,orderItemList2);
        orderService.saveRecord(order3,orderItemList3);
        orderService.saveRecord(order4,orderItemList4);
        orderService.saveRecord(order5,orderItemList5);
    }

    @Test
    public void testQuery(){
        List<Order> orderList = orderMapper.selectList(new QueryWrapper<Order>().ne("order_id", "12312312"));
        System.out.println(orderList);
    }

}
