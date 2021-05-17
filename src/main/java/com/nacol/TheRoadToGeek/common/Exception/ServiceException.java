package com.nacol.TheRoadToGeek.common.Exception;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description 业务异常
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String msg) {
        super(msg);
    }
}
