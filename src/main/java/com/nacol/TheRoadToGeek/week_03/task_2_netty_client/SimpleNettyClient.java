package com.nacol.TheRoadToGeek.week_03.task_2_netty_client;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpSourceCacheEnum;
import com.nacol.TheRoadToGeek.common.http.client.nettyclient.NacolClientHandler;
import com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_entity.RpcRequest;
import com.nacol.TheRoadToGeek.other.netty_demo.netty_demo_entity.RpcResponse;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcDecoder;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleNettyClient {
    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline()
                    .addLast(new RpcEncoder(RpcRequest.class)) //编码request
                    .addLast(new RpcDecoder(RpcResponse.class)) //解码response
                    .addLast(new NacolClientHandler()); //客户端处理类
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();

            HttpRequestDto reqeust = new HttpRequestDto()
                    .sendByNetty()
                    .setServiceCode(HttpSourceCacheEnum.LOGIN.serviceCode)
                    .init();
            //channel对象可保存在map中，供其它地方发送消息

            f.channel().write(reqeust);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        SimpleNettyClient client = new SimpleNettyClient();
        client.connect("127.0.0.1", 9989);
    }

}
