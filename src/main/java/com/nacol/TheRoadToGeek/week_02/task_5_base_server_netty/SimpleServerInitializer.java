package com.nacol.TheRoadToGeek.week_02.task_5_base_server_netty;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcDecoder;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/11
 * @Description
 * 如果走的是 http 协议就会自动进入这里
 * 自定义信道初始化，定义流水线流程
 */
public class SimpleServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //创建 pipeline
        ChannelPipeline pipeline = channel.pipeline();
        //解码request
        pipeline.addLast(new RpcDecoder(HttpRequestDto.class));
        //编码response
        pipeline.addLast(new RpcEncoder(HttpResponseDto.class));
        //自己定义的 handler
        pipeline.addLast(new SimpleServerHandler());
    }
}
