package com.nacol.shardingsphereatomikosxa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OrderPayment {

    private Long id;

    private Long OrderBaseId;

    private BigDecimal amount;

    private boolean delFlag;

}
