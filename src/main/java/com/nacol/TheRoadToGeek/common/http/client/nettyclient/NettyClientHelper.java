package com.nacol.TheRoadToGeek.common.http.client.nettyclient;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Constant;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description Netty Client 小助手
 */
@Log4j2
public class NettyClientHelper {

    /**就暂且用一个 map 缓存 bootstrap**/
    private static final Map<String, Bootstrap> bootstrapPool = new HashMap<>();

    public static HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        //获取/初始化 bootstrap (可解耦出去)
        EventLoopGroup workerGroup  = new NioEventLoopGroup();
        // Start the client.
        try {
            Bootstrap bootstrap = getBootStrap(httpRequestDto, workerGroup);
            ChannelFuture future = bootstrap.connect(httpRequestDto.getHost(), httpRequestDto.getPort()).sync();
            future.addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture arg0) throws Exception {
                    if (future.isSuccess()) {
                        log.info("Nacol Server : 连接服务器成功");
                    } else {
                        log.info("Nacol Server : 连接服务器失败");
                        future.cause().printStackTrace();
                    }
                }
            });

            httpRequestDto.init();
            future.channel().writeAndFlush(httpRequestDto);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
        return null;
    }

    public static Bootstrap getBootStrap(HttpRequestDto httpRequestDto, EventLoopGroup workerGroup) {
        Bootstrap bootstrap = bootstrapPool.get(httpRequestDto.getHostAndPort());
        if (bootstrap == null) {
            //TODO 可以根据不同的 host:port 生成不同的 bootstrap，绑定不同的 HttpClientInitializer， 绑定不通的 pipleLine
            //扩展点丢这里了，暂且偷偷懒使用同样类型的 bootstrap
            bootstrap = initClient(httpRequestDto, workerGroup);
        }
        bootstrap.group(workerGroup);
        return bootstrap;
    }

    /**
     * 可根据不同的 httpRequestDto 可以生成不同的 bootstrap 才是正解呢
     */
    public static Bootstrap initClient(HttpRequestDto httpRequestDto, EventLoopGroup workerGroup) {
        httpRequestDto.init();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new NacolClientIntitiallizer());
//            bootstrap.handler(new HttpClientInitializer());
//            bootstrap.handler(new SimpleClientInitializer());
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
        return bootstrap;
    }

}
