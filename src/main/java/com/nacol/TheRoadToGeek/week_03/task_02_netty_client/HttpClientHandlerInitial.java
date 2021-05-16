package com.nacol.TheRoadToGeek.week_03.task_02_netty_client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;


public class HttpClientHandlerInitial extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //客户端编码、解码器，请求编码，响应解码
        pipeline.addLast(new HttpClientCodec());
        //http聚合器，将http请求聚合成一个完整报文
        pipeline.addLast(new HttpObjectAggregator( 1024 * 1024));
        //http响应解压缩
        pipeline.addLast(new HttpContentDecompressor());
        //业务handler
        pipeline.addLast(new HttpClientBusinessHandler());

    }
}