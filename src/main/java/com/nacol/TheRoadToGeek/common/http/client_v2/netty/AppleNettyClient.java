package com.nacol.TheRoadToGeek.common.http.client_v2.netty;

import com.nacol.TheRoadToGeek.common.http.client.nettyclient.NacolClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class AppleNettyClient implements BaseNettyClient{

    private final String host;
    private final int port;
    private Channel channel;

    //连接服务端的端口号地址和端口号
    public AppleNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws Exception {
        final EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)  // 使用NioSocketChannel来作为连接用的channel类
                .handler(new ChannelInitializer<SocketChannel>() { // 绑定连接初始化器
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("正在连接中...");
                        ChannelPipeline pipeline = ch.pipeline();
                        //httpServer 编码器
                        pipeline.addLast(new HttpServerCodec());
                        //报文聚合器
                        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
                        //客户端处理类
                        pipeline.addLast(new NacolClientHandler());
                    }
                });
        //发起异步连接请求，绑定连接端口和host信息
        final ChannelFuture future = b.connect(host, port).sync();

        future.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture arg0) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接服务器成功");

                } else {
                    System.out.println("连接服务器失败");
                    future.cause().printStackTrace();
                    group.shutdownGracefully(); //关闭线程组
                }
            }
        });

        this.channel = future.channel();
    }

    @Override
    public Channel getChannel() {
        return channel;
    }
}