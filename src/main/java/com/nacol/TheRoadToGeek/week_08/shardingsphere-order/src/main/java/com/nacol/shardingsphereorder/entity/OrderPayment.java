package com.nacol.shardingsphereorder.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@TableName("order_payment")
public class OrderPayment {

    private Long id;

    private Long orderBaseId;

    private BigDecimal amount;

    private Integer delFlag;
}
