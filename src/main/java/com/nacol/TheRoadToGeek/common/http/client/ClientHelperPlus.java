package com.nacol.TheRoadToGeek.common.http.client;

import com.nacol.TheRoadToGeek.common.Exception.ServiceException;
import com.nacol.TheRoadToGeek.common.Exception.StrategyNotFoundException;
import com.nacol.TheRoadToGeek.common.annotation.HttpFilter;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client.httpclient.HttpClientHelper2;
import com.nacol.TheRoadToGeek.common.http.client.nettyclient.NacolNettyClientHelper;
import org.springframework.stereotype.Component;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto.HTTP_CLIENT;
import static com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto.NETTY;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description http 发送助手
 * ps: 此类关注与解耦调用和被调用方的具体实现，以后可改为策略模式
 */
@Component
public class ClientHelperPlus {

    @HttpFilter
    public HttpResponseDto sendRequest(HttpRequestDto requestDto) {
        if (requestDto == null) {
            throw new ServiceException("请求不能为 null ");
        }
        HttpResponseDto responseDto;
        // use httpclient
        if (HTTP_CLIENT.equals(requestDto.getTechnology())) {
//            responseDto = HttpClientHelper.sendRequest(requestDto);
            responseDto = HttpClientHelper2.sendRequest(requestDto);
        }
        // use netty
        else if (NETTY.equals(requestDto.getTechnology())) {
//            responseDto = NettyClientHelper.sendRequest(requestDto);
            responseDto = NacolNettyClientHelper.sendRequest(requestDto);
        } else {
            throw new StrategyNotFoundException("未找到发送技术策略.");
        }
        responseDto.setServiceCode(requestDto.getServiceCode());
        return responseDto;
    }

}
