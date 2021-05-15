package com.nacol.TheRoadToGeek.common.http.request_strategy.impl;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.request_strategy.ParamStrategy;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description 设置 get 请求参数
 */
public class GetParam implements ParamStrategy {

    @Override
    public void setParam(HttpUriRequest uriRequest, HttpRequestDto httpRequestDto) {
        Object sendParam = httpRequestDto.getParam();
        if (sendParam instanceof Map) {
            HttpGet httpGet = (HttpGet) uriRequest;
            List<NameValuePair> pairs = new ArrayList<>();
            StringBuilder noParamUri = new StringBuilder(httpGet.getURI().toString());
            Map param = (Map) sendParam;
            if (MapUtils.isNotEmpty(param)) {
                noParamUri.append("?").append(System.currentTimeMillis());
                for (Object entry : param.entrySet()) {
                    Map.Entry<Object, Object> objectEntry = (Map.Entry<Object, Object>) entry;
                    noParamUri
                            .append("&")
                            .append(objectEntry.getKey())
                            .append("=")
                            .append(objectEntry.getValue());
                }
                httpGet.setURI(URI.create(noParamUri.toString()));
            }
        } else {
            throw new RuntimeException("Get请求的参数类型必须为Map类型!");
        }
    }

}
