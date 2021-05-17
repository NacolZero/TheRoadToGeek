package com.nacol.TheRoadToGeek.common.http.param_strategy.impl;

import com.nacol.TheRoadToGeek.common.Exception.ServiceException;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.param_strategy.ParamStrategy;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description 设置 POST 的 form 类型请求参数
 */
public class FormParam implements ParamStrategy {

    @Override
    public void setParam(HttpUriRequest uriRequest, HttpRequestDto httpRequestDto) {
        Object sendParam = httpRequestDto.getParam();
//        Object sendParam = httpRequestDto;
        HttpPost httpPost = (HttpPost) uriRequest;
        if (sendParam instanceof Map) {
            List<NameValuePair> pairs = new ArrayList<>();
            Map param = (Map) sendParam;
            for (Object entry : param.entrySet()) {
                Map.Entry<Object, Object> objectEntry = (Map.Entry<Object, Object>) entry;
                pairs.add(
                    new BasicNameValuePair(objectEntry.getKey().toString(), objectEntry.getValue().toString()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8));
        } else {
            throw new ServiceException("Http Post请求中form类型的参数必须是Map.");
        }
    }

}
