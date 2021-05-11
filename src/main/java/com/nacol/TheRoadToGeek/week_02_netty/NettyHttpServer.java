package com.nacol.TheRoadToGeek.week_02_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/11
 * @Description Netty 服务端启动类
 */
public class NettyHttpServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 8808;
        EventLoopGroup bosssGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //各种 channel 参数
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //绑定 EvenLoop
            bootstrap.group(bosssGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //指定我们另外一个类 ：流水线定义
                    .childHandler(new HttpInitializer());

            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("开启 Netty Http 服务器，监听地址和端口为 : http://127.0.0.1:" + port + '/');
            channel.closeFuture().sync();
        } finally {
            bosssGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
