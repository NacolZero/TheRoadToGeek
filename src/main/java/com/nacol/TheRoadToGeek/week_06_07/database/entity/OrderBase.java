package com.nacol.TheRoadToGeek.week_06_07.database.entity;

import lombok.Data;

@Data
public class OrderBase {

    private String id;

    private Long createTime;

    private Long updateTime;

    private Integer DelFlag;

}
