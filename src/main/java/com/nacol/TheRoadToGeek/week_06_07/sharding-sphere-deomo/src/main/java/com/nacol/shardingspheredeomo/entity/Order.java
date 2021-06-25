package com.nacol.shardingspheredeomo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("t_order")
public class Order {

    private static final long serialVersionUID = 1L;
    private Long orderId;
    private Long userId;
    private String status;

    public Order(){}

    public Order(Long orderId,Long userId,String status){
        this.orderId=orderId;
        this.userId=userId;
        this.status=status;
    }

}
