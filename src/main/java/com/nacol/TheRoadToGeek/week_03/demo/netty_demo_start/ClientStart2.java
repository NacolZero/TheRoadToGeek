package com.nacol.TheRoadToGeek.week_03.demo.netty_demo_start;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.week_03.demo.netty_demo_client.NettyClient2;
import io.netty.channel.Channel;

public class ClientStart2 {

    public static void main(String[] args) throws Exception {
        NettyClient2 client = new NettyClient2("127.0.0.1", 9989);
        //启动client服务
        client.start();

        Channel channel = client.getChannel();
        //消息体
        HttpRequestDto request = new HttpRequestDto()
                .setServiceCode("12312312312312312321");
        //channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(request);
    }

}
