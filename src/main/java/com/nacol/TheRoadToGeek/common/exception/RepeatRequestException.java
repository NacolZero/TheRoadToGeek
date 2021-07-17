package com.nacol.TheRoadToGeek.common.exception;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/17
 * @Description 重复提交请求异常
 */
public class RepeatRequestException extends RuntimeException{

    public RepeatRequestException() {}

    public RepeatRequestException(String message) {
        super(message);
    }

}
