package com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_start;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_client.NettyClient2;
import io.netty.channel.Channel;

public class ClientStart2 {

    public static void main(String[] args) throws Exception {
        NettyClient2 client = new NettyClient2("127.0.0.1", 9989);
        //启动client服务
        client.start();

        Channel channel = client.getChannel();
        //消息体
//        HttpRequestDto request = new HttpRequestDto().setParam("123312123321231").setPort(1111).setHost("123").setUri("321").setHttpType("321312");
        HttpRequestDto request = new HttpRequestDto().setParam("123312123321231").setServiceCode("login").init();
        System.out.println("----------------------------     request : " + request);
        //channel对象可保存在map中，供其它地方发送消息
        channel.writeAndFlush(request);
    }

}
