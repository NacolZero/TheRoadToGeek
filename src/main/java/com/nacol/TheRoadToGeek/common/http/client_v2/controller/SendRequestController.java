package com.nacol.TheRoadToGeek.common.http.client_v2.controller;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client_v2.ClientTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpSourceCacheEnum.LOGIN;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/6/5
 * @Description 发送请求
 */
@Log4j2
@RestController
@RequestMapping("send-request")
public class SendRequestController {

    @Autowired
    ClientTemplate clientTemplate;

    @PostMapping("/send")
    public HttpResponseDto send() {
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
                .log()
                //业务 code
                .setServiceCode(LOGIN.serviceCode);

        HttpResponseDto response = clientTemplate.sendRequest(httpRequestDto);

        return response;
    }

}
