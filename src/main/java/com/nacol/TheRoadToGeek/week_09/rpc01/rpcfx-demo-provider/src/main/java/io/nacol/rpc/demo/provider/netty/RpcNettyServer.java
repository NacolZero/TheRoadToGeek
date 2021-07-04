package io.nacol.rpc.demo.provider.netty;

import io.nacol.rpc.netty.common.RpcDeconder;
import io.nacol.rpc.netty.common.RpcEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RpcNettyServer {

    private static final int PORT = 8866;
    private static EventLoopGroup boss;
    private static EventLoopGroup worker;

    public static void destory() {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }

    public static void run() throws InterruptedException {
        boss = new NioEventLoopGroup(1);
        worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer(){
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline()
                                .addLast(new RpcEncoder())
                                .addLast(new RpcDeconder())
                                .addLast(new RpcServerHandler());
                    }
                });
        Channel channel = bootstrap.bind(PORT).sync().channel();
        log.info("netty server listen in port : {}", PORT);
        channel.closeFuture().sync();
    }

}
