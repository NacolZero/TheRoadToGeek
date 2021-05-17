package com.nacol.TheRoadToGeek.common.http.param_strategy.impl;

import com.nacol.TheRoadToGeek.common.entity.http.HttpConstants;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.param_strategy.ParamStrategy;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.nio.charset.StandardCharsets;


/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description 设置 POST 的 xml 请求参数
 */
public class XmlParam implements ParamStrategy {

    @Override
    public void setParam(HttpUriRequest uriRequest, HttpRequestDto httpRequestDto) {
        Object sendParam = httpRequestDto.getParam();
        HttpPost httpPost = (HttpPost) uriRequest;
        StringEntity entityParams;
        if (sendParam instanceof String) {
            entityParams = new StringEntity(sendParam.toString(), StandardCharsets.UTF_8);
            httpPost.addHeader(HttpConstants.CONTENT_TYPE, HttpConstants.TEXT_XML);
        } else {
            throw new RuntimeException("xml类型必须使用String字符串。");
        }
        httpPost.setEntity(entityParams);
    }

}
