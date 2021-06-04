package com.nacol.TheRoadToGeek.common.http.gateway.netty;

import com.nacol.TheRoadToGeek.week_02.task_5_base_server_netty.HttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/6/4
 * @Description 初始化（apple 版本）
 */
public class AppleHttpServerInitializer extends ChannelInitializer<SocketChannel> {

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
        pipeline.addLast(new AppleHttpServerHandler());
    }
}
