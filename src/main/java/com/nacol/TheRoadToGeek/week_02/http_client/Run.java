package com.nacol.TheRoadToGeek.week_02.http_client;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Run {

    public static void main(String[] args) throws IOException {
        //自定义请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("testHeader", "123");

        //请求参数
        Map<String, String> getReqParams = new HashMap<>();
        getReqParams.put("name", "ahahahaha");

        //初始化请求参数
        HttpRequestDto httpRequestDto = new HttpRequestDto()
                //业务类型
                .setServiceCode("pay")
                //自定义请求头（暂时未使用配置的方式）
                .setCustomHeaders(headers)
                .setParam(getReqParams);

        //发送请求
        Object result = HttpClientHelper.sendHttpRequest(httpRequestDto);
        System.out.println("result : " + result);

    }

}
