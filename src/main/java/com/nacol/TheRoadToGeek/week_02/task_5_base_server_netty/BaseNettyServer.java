package com.nacol.TheRoadToGeek.week_02.task_5_base_server_netty;

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
public class BaseNettyServer {

    public static void main(String[] args) throws InterruptedException {
        int port = 9989;
        EventLoopGroup bosssGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //各种 channel 参数
            //创建过程中的中间状态的 TCP 请求最大数量
            bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    //启用 Nagle 算法
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //心跳：有啥用？
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //让 TCP 的 WAIT_TIME 不需等待
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    //接收缓冲区，相当于接收的数据包大小，默认 1500 - 20（IP） - 20（TCP）
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    //发送缓冲区，相当于发送的数据包大小，默认 1500 - 20（IP） - 20（TCP）
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    //支持多个进程多个线程绑定同一个端口，多个套接字可使用一个端口，不再等“锁”
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //绑定 EvenLoop
            bootstrap.group(bosssGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
//                    //指定我们另外一个类 ：流水线定义
//                    .childHandler(new HttpServerInitializer());
                    //
                    .childHandler(new NacolServerInitalizer());

            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("开启 Netty Http 服务器，监听地址和端口为 : http://127.0.0.1:" + port + '/');
            channel.closeFuture().sync();
        } finally {
            bosssGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
