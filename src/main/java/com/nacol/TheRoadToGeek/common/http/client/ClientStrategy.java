package com.nacol.TheRoadToGeek.common.http.client;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description 发送消息策略
 */
public interface ClientStrategy {

    public HttpResponseDto sendRequest(HttpRequestDto requestDto);

}
