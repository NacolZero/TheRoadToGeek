package com.nacol.TheRoadToGeek.common.http.client.nettyclient;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.channel.*;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description Netty Client 小助手
 */
@Log4j2
public class NacolNettyClientHelper {

    public static Map<String, NacolNettyClient> clientPool = new HashMap<>();

    public static HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        NacolNettyClient client = getClient(httpRequestDto);
        Channel channel = client.getChannel();
        //channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(httpRequestDto);
        return null;
    }

    private static NacolNettyClient getClient(HttpRequestDto httpRequestDto) {
        String host = httpRequestDto.getHost();
        int port = httpRequestDto.getPort();
        NacolNettyClient client = clientPool.get(host+port);
        if (client == null) {
            client = new NacolNettyClient(httpRequestDto.getHost(), httpRequestDto.getPort());
            //启动client服务
            try {
                client.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            clientPool.put(host+port, client);
        }
        return client;
    }

}
