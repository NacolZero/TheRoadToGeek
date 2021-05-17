package com.nacol.TheRoadToGeek.week_03.netty_gateway;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcDecoder;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RpcEncoder;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.inbound_handler.InboundHandler;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.other.IpFilter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ipfilter.RuleBasedIpFilter;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/11
 * @Description
 * 如果走的是 http 协议就会自动进入这里
 * 自定义信道初始化，定义流水线流程
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    private String serverCode;

    public HttpServerInitializer(String serverCode) {
        this.serverCode = serverCode;
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        //责任链模式 ChannelHandlerContext
        //创建 pipeline
        channel.pipeline()
        //ip 过滤器
            .addFirst(new RuleBasedIpFilter(new IpFilter()))
            .addLast(new RpcDecoder(HttpRequestDto.class)) //解码request
            .addLast(new RpcEncoder(HttpResponseDto.class)) //编码response
//        //httpServer 编码器
//        pipeline.addLast(new HttpServerCodec());
//        //报文聚合器
//        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        //自己定义的 handler
            .addLast(new InboundHandler(this.serverCode));
    }
}
