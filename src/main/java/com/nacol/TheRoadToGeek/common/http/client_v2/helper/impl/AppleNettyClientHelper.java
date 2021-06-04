package com.nacol.TheRoadToGeek.common.http.client_v2.helper.impl;

import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client_v2.helper.IHttpClient;
import com.nacol.TheRoadToGeek.common.http.client_v2.netty.AppleNettyClient;
import com.nacol.TheRoadToGeek.common.http.client_v2.netty.BaseNettyClient;
import com.nacol.TheRoadToGeek.common.http.client_v2.netty.NettyClientPool;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/6/4
 * @Description
 */
@Component
public class AppleNettyClientHelper implements IHttpClient {

    @Autowired
    NettyClientPool nettyClientPool;

    @Override
    public HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        //基于接口获取 client 端
        BaseNettyClient client = nettyClientPool.getClient(httpRequestDto, AppleNettyClient.class);
        Channel channel = client.getChannel();

        //初始化请求
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.POST,
                httpRequestDto.getUri(),
                Unpooled.copiedBuffer(JSONObject.toJSONString(httpRequestDto.getParam()),
                CharsetUtil.UTF_8));

        //请求头处理
        request.headers()
            .set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.APPLICATION_JSON)
            .setInt(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        httpRequestDto.getCustomHeaders().forEach((k, v)->request.headers().set(k, v));

        //TODO channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(httpRequestDto);

        //TODO 调研 netty 如何同步获取 response 用于回复
        return new HttpResponseDto();
    }

}
