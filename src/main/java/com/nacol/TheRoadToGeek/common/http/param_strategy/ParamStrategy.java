package com.nacol.TheRoadToGeek.common.http.param_strategy;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description 请求参数策略定义
 */
public interface ParamStrategy {

    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2020/12/10
     * @Description 设置请求参数
     */
    void setParam(HttpUriRequest uriRequest, HttpRequestDto httpRequestDto);

}
