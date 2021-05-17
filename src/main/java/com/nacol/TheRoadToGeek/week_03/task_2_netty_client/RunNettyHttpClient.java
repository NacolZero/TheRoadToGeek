package com.nacol.TheRoadToGeek.week_03.task_2_netty_client;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client.ClientHelper;

public class RunNettyHttpClient {

    public static void main(String[] args) {
//        //自定义请求头
//        Map<String, String> headers = new HashMap<>();
//        headers.put("testHeader", "123");
//
//        //请求参数
//        Map<String, String> getReqParams = new HashMap<>();
//        getReqParams.put("name", "Nacol");

        //初始化请求参数
        //TODO 实现了捡漏的 BootStrap 缓存，以及基本（写死^@^）的 netty http 发送
        //问题:1. 没找到哪里获取请求参数；2.没弄清楚怎么让返回的数据到这一层。
        HttpRequestDto httpRequestDto = new HttpRequestDto()
                //使用 HttpClient 方式发送
                .sendByNetty()
                //业务类型（自定义）
                .setServiceCode("test")
//                //自定义请求头（暂时未使用配置的方式）
//                .setCustomHeaders(headers)
//                //请求参数
//                .setParam(getReqParams)
//                //输出发送日志
//                .log()
                ;
        HttpResponseDto responseDto = ClientHelper.sendRequest(httpRequestDto);
        System.out.println("--------------------------------------------------------------------------------------- : " +  responseDto.getResultData());
    }
}
