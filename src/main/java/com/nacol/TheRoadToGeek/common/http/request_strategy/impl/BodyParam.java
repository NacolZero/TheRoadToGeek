package com.nacol.TheRoadToGeek.common.http.request_strategy.impl;

import com.alibaba.fastjson.JSON;
import com.nacol.TheRoadToGeek.common.entity.http.HttpConstants;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.request_strategy.ParamStrategy;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.nio.charset.StandardCharsets;


/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description 设置 POST 的 body 类型请求参数
 */
public class BodyParam implements ParamStrategy {

    @Override
    public void setParam(HttpUriRequest uriRequest, HttpRequestDto httpRequestDto) {
        Object sendParam = httpRequestDto.getParam();
        HttpPost httpPost = (HttpPost) uriRequest;
        StringEntity entityParams;
        if (sendParam instanceof String) {
            entityParams = new StringEntity(sendParam.toString(), StandardCharsets.UTF_8);
            httpPost.addHeader(HttpConstants.CONTENT_TYPE, HttpConstants.APPLICATION_JSON);
        } else {
            entityParams = new StringEntity(JSON.toJSONString(sendParam), StandardCharsets.UTF_8);
        }
        httpPost.setEntity(entityParams);
    }

}
