package com.nacol.TheRoadToGeek.week_02.task_6_http_client;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client.ClientHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpSourceCacheEnum.LOGIN;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description 具体实现在 package com.nacol.TheRoadToGeek.common.http.send_param.impl 中
 */
public class SendHttpRequest {

    public static void main(String[] args) throws IOException {
        //get: params
//        HttpRequestDto requestDto = initBaseData().setServiceCode(TEST.serviceCode);
//        HttpResponseDto responseDto1 = ClientHelper.sendRequest(requestDto);
//        System.out.println(responseDto1.getResultData());

//        //post: form
//        HttpRequestDto formRequest = initBaseData().setServiceCode(PAY.serviceCode);
//        HttpResponseDto responseDto2 = ClientHelper.sendRequest(formRequest);
//        System.out.println(responseDto2.getResultData());
//
        //post: body(json)
        HttpRequestDto bodyRequest = initBaseData().setServiceCode(LOGIN.serviceCode);
        HttpResponseDto responseDto3 = ClientHelper.sendRequest(bodyRequest);
        System.out.println(responseDto3.getResultData());


//        //xml
//        HttpRequestDto xmlRequest = initBaseData().setServiceCode("xxx");
//        HttpResponseDto xmlDto3 = HttpSendHelper.sendRequest(xmlRequest);
//        System.out.println(responseDto3.getResultData());
    }

    private static HttpRequestDto initBaseData() {
        //自定义请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("testHeader", "123");

        //请求参数
        Map<String, String> getReqParams = new HashMap<>();
        getReqParams.put("name", "Nacol");

        //初始化请求参数
        HttpRequestDto httpRequestDto = new HttpRequestDto()
                //使用 HttpClient 方式发送
                .sendByHttpClient()
                //业务类型（自定义）
                .setServiceCode("pay")
                //自定义请求头（暂时未使用配置的方式）
                .setCustomHeaders(headers)
                //请求参数
                .setParam(getReqParams)
                //输出发送日志
                .log();
        return httpRequestDto;
    }

}
