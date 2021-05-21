package com.nacol.TheRoadToGeek.common.http.client.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.Exception.ServiceException;
import com.nacol.TheRoadToGeek.common.entity.http.HttpConstants;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.param_strategy.ParamStrategy;
import com.nacol.TheRoadToGeek.common.http.param_strategy.ParamStrategyFactory;
import com.nacol.TheRoadToGeek.week_03.task_6_gateway_pool.GateWayThreadPool;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpConstants.*;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description Http 小助手
 * ps: 该类只应该组织流程，当复杂度提升后可把具体执行的流程完全解耦出去.
 */
public class HttpClientHelper2 {

    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2021/5/15
     * @Description 发送请求
     */
    public static HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        //STEP 初始化请求
        HttpPost request = initRequest(httpRequestDto);
        //STEP 初始化参数
        initParams(request, httpRequestDto);
        //STEP 执行请求
        HttpResponseDto result = submitRequest(request, httpRequestDto);//多线程
//        HttpResponseDto result = executeRequest(request, httpRequestDto);//傻呆呆单线程
        //STEP 日志(不该写这里)
        recordLog(httpRequestDto);
        return result;
    }

    private static HttpPost initRequest(HttpRequestDto httpRequestDto) {
        //拼接通信地址
        httpRequestDto.init();
        //初始化请求
        HttpPost request = new HttpPost(httpRequestDto.getUrl());
        return request;
    }

    private static void initParams(HttpPost request, HttpRequestDto httpRequestDto) {
        //STEP 设置请求头
        if (MapUtils.isNotEmpty(httpRequestDto.getCustomHeaders())) {
            httpRequestDto.getCustomHeaders().forEach((k,v)->request.addHeader(k, v));
        }
        StringEntity entityParams = new StringEntity(JSON.toJSONString(httpRequestDto), StandardCharsets.UTF_8);
        request.setEntity(entityParams);
    }

    private static HttpResponseDto submitRequest(HttpPost request, HttpRequestDto httpRequestDto) {
        HttpResponseDto responseDto = null;
        try {
            responseDto = (HttpResponseDto)GateWayThreadPool.submit(()->executeRequest(request, httpRequestDto)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    private static HttpResponseDto executeRequest(HttpUriRequest request, HttpRequestDto httpRequestDto) {
        //STEP 初始化 http 类型
        CloseableHttpClient httpClient;
        if (httpRequestDto.isHttps()) {
            throw new ServiceException("暂时不支持 https");
        }
        // 默认 http
        else {
            httpClient = HttpClients.createDefault();
        }
        //STEP 执行请求
        HttpResponseDto responseDto = null;
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            String resultStr = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
            //转化约定的返回结果类型
            responseDto = JSONObject.parseObject(resultStr, HttpResponseDto.class);
            httpClient.close();
            httpResponse.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        return responseDto;
    }

    private static void recordLog(HttpRequestDto httpRequestDto) {
        if (httpRequestDto.isLogRecord()) {
            System.out.println(LocalDateTime.now() + ":" + httpRequestDto.getUrl());
        }
    }
}
