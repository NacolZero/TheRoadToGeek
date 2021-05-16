package com.nacol.TheRoadToGeek.common.http.client.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.Exception.ServiceException;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.param_strategy.ParamStrategy;
import com.nacol.TheRoadToGeek.common.http.param_strategy.ParamStrategyFactory;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpConstants.*;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description Http 小助手
 * ps: 该类只应该组织流程，因为 demo 暂不把这些执行流程完全解耦出去.
 */
public class HttpClientHelper {

    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2021/5/15
     * @Description 发送请求
     */
    public static HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        //STEP 初始化请求
        HttpUriRequest request = initRequest(httpRequestDto);
        //STEP 初始化参数
        initParams(request, httpRequestDto);
        //STEP 执行请求
        HttpResponseDto result = executeRequest(request, httpRequestDto);
        //STEP 日志(不该写这里)
        recordLog(httpRequestDto);
        return result;
    }

    private static HttpUriRequest initRequest(HttpRequestDto httpRequestDto) {
        //拼接通信地址
        httpRequestDto.initUrl();
        //初始化请求
        HttpUriRequest request;
        if (HTTP_POST.equals(httpRequestDto.getHttpType())) {
            request = new HttpPost(httpRequestDto.getUrl());
        } else if (HTTP_GET.equals(httpRequestDto.getHttpType())) {
            request = new HttpGet(httpRequestDto.getUrl());
        } else {
            throw new ServiceException("http type is wrong.");
        }
        return request;
    }

    private static void initParams(HttpUriRequest request, HttpRequestDto httpRequestDto) {
        //STEP 设置请求头
        if (MapUtils.isNotEmpty(httpRequestDto.getCustomHeaders())) {
            httpRequestDto.getCustomHeaders().forEach((k,v)->request.addHeader(k, v));
//            httpRequestDto.getCustomHeaders().forEach(request::addHeader);
        }

        //STEP 参数验证
        if (httpRequestDto.getParam() == null) {
            //TODO 可完善验证
            return;
        }
        //STEP 获取对应参数设置的策略
        ParamStrategy paramStrategy = ParamStrategyFactory.getParamStrategy(httpRequestDto);
        paramStrategy.setParam(request, httpRequestDto);
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
