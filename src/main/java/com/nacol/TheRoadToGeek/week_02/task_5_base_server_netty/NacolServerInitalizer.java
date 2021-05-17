package com.nacol.TheRoadToGeek.week_02.task_5_base_server_netty;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcDecoder;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class NacolServerInitalizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline()
                .addLast(new RpcDecoder(HttpRequestDto.class)) //解码request
                .addLast(new RpcEncoder(HttpResponseDto.class)) //编码response
                .addLast(new NacolServerHandler()); //使用ServerHandler类来处理接收到的消息
    }
}
