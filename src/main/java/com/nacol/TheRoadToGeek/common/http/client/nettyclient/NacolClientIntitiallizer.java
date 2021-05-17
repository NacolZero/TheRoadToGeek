package com.nacol.TheRoadToGeek.common.http.client.nettyclient;

import com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_entity.RpcRequest;
import com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_entity.RpcResponse;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcDecoder;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class NacolClientIntitiallizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
            .addLast(new RpcEncoder(RpcRequest.class)) //编码request
            .addLast(new RpcDecoder(RpcResponse.class)) //解码response
            .addLast(new NacolClientHandler()); //客户端处理类
    }
}
