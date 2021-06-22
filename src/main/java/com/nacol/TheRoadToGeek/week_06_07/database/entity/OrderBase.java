package com.nacol.TheRoadToGeek.week_06_07.database.entity;

import java.math.BigDecimal;

public class OrderBase {

    private String id;

    private String cmdBaseId;

    private String cmdType;

    private BigDecimal originalPrice;

    private BigDecimal currentPrice;

    private Integer stockNumber;

    private Long createTime;

    private Long updateTime;

    private Integer DelFlag;

}
