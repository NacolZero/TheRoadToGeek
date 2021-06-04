package com.nacol.TheRoadToGeek.common.http.client;

import com.nacol.TheRoadToGeek.common.exception.StrategyNotFoundException;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client.httpclient.HttpClientHelper2;
import com.nacol.TheRoadToGeek.common.http.client.nettyclient.NacolNettyClientHelper;

import static com.nacol.TheRoadToGeek.common.http.client_v2.config.HttpClientConfig.HTTP_CLIENT;
import static com.nacol.TheRoadToGeek.common.http.client_v2.config.HttpClientConfig.NETTY;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description http 发送助手
 * ps: 此类关注与解耦调用和被调用方的具体实现，以后可改为策略模式
 */
public class ClientHelper {

    public static HttpResponseDto sendRequest(HttpRequestDto requestDto) {
        HttpResponseDto responseDto;
        // use httpclient
        if (HTTP_CLIENT.tecName.equals(requestDto.getTechnology())) {
//            responseDto = HttpClientHelper.sendRequest(requestDto);
            responseDto = HttpClientHelper2.sendRequest(requestDto);
        }
        // use netty
        else if (NETTY.tecName.equals(requestDto.getTechnology())) {
//            responseDto = NettyClientHelper.sendRequest(requestDto);
            responseDto = NacolNettyClientHelper.sendRequest(requestDto);
        } else {
            throw new StrategyNotFoundException("未找到发送技术策略.");
        }
        return responseDto;
    }

}
