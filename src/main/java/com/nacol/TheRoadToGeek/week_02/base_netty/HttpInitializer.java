package com.nacol.TheRoadToGeek.week_02.base_netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/11
 * @Description
 * 如果走的是 http 协议就会自动进入这里
 * 自定义信道初始化，定义流水线流程
 */
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //创建 pipeline
        ChannelPipeline pipeline = channel.pipeline();
        //责任链模式 ChannelHandlerContext
        //httpServer 编码器
        pipeline.addLast(new HttpServerCodec());
        //报文聚合器
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        //自己定义的 handler
        pipeline.addLast(new HttpHandler());
    }
}
