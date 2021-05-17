package com.nacol.TheRoadToGeek.common.http.client.nettyclient;

import com.alibaba.fastjson.JSONObject;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;

import java.util.HashMap;
import java.util.Map;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpSourceCacheEnum.TEST;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description Netty Client 小助手
 */
public class NettyClient {

    /**就暂且用一个 map 缓存 bootstrap**/
    private static final Map<String, Bootstrap> bootstrapPool = new HashMap<>();

    public static HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        //获取/初始化 bootstrap (可解耦出去)
        Bootstrap bootstrap = getBootStrap(httpRequestDto);
        // Start the client.
        try {
            ChannelFuture f = bootstrap.connect(httpRequestDto.getHost(), httpRequestDto.getPort()).sync();
            f.channel().write(JSONObject.toJSONString(httpRequestDto));
            f.channel().flush();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bootstrap getBootStrap(HttpRequestDto httpRequestDto) {
        Bootstrap bootstrap = bootstrapPool.get(httpRequestDto.getHostAndPort());
        if (bootstrap == null) {
            //TODO 可以根据不同的 host:port 生成不同的 bootstrap，绑定不同的 HttpClientInitializer， 绑定不通的 pipleLine
            //扩展点丢这里了，暂且偷偷懒使用同样类型的 bootstrap
            bootstrap = initClient(httpRequestDto);
        }
        return bootstrap;
    }

    /**
     * 可根据不同的 httpRequestDto 可以生成不同的 bootstrap 才是正解呢
     */
    public static Bootstrap initClient(HttpRequestDto httpRequestDto) {
        httpRequestDto.init();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new HttpClientInitializer());
//            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
//                    ch.pipeline().addLast(new HttpResponseDecoder());
////                     客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
//                    ch.pipeline().addLast(new HttpRequestEncoder());
//                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler());
//                }
//            });
            bootstrapPool.put(httpRequestDto.getHostAndPort(), bootstrap);
        } finally {
//            workerGroup.shutdownGracefully();
        }
        return bootstrap;
    }

}
