package com.nacol.TheRoadToGeek.common.exception;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description 找不到策略异常
 */
public class StrategyNotFoundException extends RuntimeException{

    public StrategyNotFoundException(String msg) {
        super(msg);
    }

}
