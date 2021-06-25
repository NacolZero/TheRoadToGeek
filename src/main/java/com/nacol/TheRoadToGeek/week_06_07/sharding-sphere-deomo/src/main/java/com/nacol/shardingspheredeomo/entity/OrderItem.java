package com.nacol.shardingspheredeomo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order_item")
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long userId;
    private String status;

    public OrderItem(){}

    public OrderItem(Long orderItemId,Long orderId,Long userId,String status){
        this.orderItemId=orderItemId;
        this.orderId=orderId;
        this.userId=userId;
        this.status=status;
    }
}
