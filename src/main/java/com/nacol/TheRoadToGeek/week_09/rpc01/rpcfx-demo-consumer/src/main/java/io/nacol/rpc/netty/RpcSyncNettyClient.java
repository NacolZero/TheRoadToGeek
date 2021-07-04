package io.nacol.rpc.netty;

import com.alibaba.fastjson.JSON;
import io.nacol.rpc.api.RpcRequest;
import io.nacol.rpc.api.RpcResponse;
import io.nacol.rpc.netty.cache.NettyClientCache;
import io.nacol.rpc.netty.handler.RpcSynHandler;
import io.nacol.rpc.netty.common.RpcDeconder;
import io.nacol.rpc.netty.common.RpcEncoder;
import io.nacol.rpc.netty.common.RpcMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/3
 * @Description rpc 同步 Netty Client
 */
@Log4j2
public class RpcSyncNettyClient {

    private NettyClientCache nettyClientCache = new NettyClientCache();
    private EventLoopGroup clientGroup = new NioEventLoopGroup();

    public RpcResponse request(RpcRequest rpcRequest) throws URISyntaxException, InterruptedException {
        RpcMessage rpcMessage = covert(rpcRequest);

        //STEP 使用缓存 channel
        if (nettyClientCache.exist(rpcRequest)) {
            log.info("user cache channel");
            Channel channel = nettyClientCache.getChannel(rpcRequest);
            if (!channel.isActive() || !channel.isWritable() || !channel.isOpen()) {
                log.error("request :{}. channel cant reuse.", JSON.toJSONString(rpcRequest));
            } else {
                try {
                    RpcSynHandler handler = new RpcSynHandler(new CountDownLatch(1));
                    channel.pipeline().replace("clientHandler", "clientHandler", handler);
                    channel.writeAndFlush(rpcMessage).sync();
                    return handler.getRpcResponse();
                } catch (Exception e) {
                    e.printStackTrace();
                    channel.close();
                    nettyClientCache.removeChannel(rpcRequest);
                }
            }
        }
        //STEP 新增 channel
        log.info("new channel");
        RpcSynHandler handler = new RpcSynHandler(new CountDownLatch(1));
        Bootstrap bootStrap = new Bootstrap();
        bootStrap.group(clientGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.AUTO_CLOSE, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new RpcEncoder())
                                .addLast(new RpcDeconder())
                                .addLast("clientHandler", handler);
                    }
                });
        URI uri = new URI(rpcRequest.getUrl());
        Channel channel = bootStrap.connect(uri.getHost(), uri.getPort()).sync().channel();
        nettyClientCache.addCache(rpcRequest, channel);
        channel.writeAndFlush(rpcMessage).sync();
        return handler.getRpcResponse();
    }


//        //STEP 新增 channel
//        RpcSynHandler handler = new RpcSynHandler(new CountDownLatch(1));
//        URI uri = new URI(rpcRequest.getUrl());
//        Channel channel = initChannel(uri.getHost(), uri.getPort());
//        nettyClientCache.addCache(rpcRequest, channel);
//        log.info("replace handler");
//        channel.pipeline().replace("clientHandler", "clientHandler", handler);
//        channel.writeAndFlush(rpcMessage).sync();
//        return handler.getRpcResponse();
//    }
//
//    private Channel initChannel(String host, int port) throws InterruptedException {
//        log.info("init new channel");
//        Bootstrap bootStrap = new Bootstrap();
//        bootStrap.group(clientGroup)
//            .channel(NioSocketChannel.class)
//            .option(ChannelOption.SO_REUSEADDR, true)
//            .option(ChannelOption.TCP_NODELAY, true)
//            .option(ChannelOption.AUTO_CLOSE, true)
//            .option(ChannelOption.SO_KEEPALIVE, true)
//            .handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    socketChannel.pipeline()
//                            .addLast(new RpcDeconder())
//                            .addLast(new RpcEncoder())
//                            .addLast("clientHandler", new RpcSynHandler());
//                }
//            });
//        return bootStrap.connect(host, port).sync().channel();
//    }

    private RpcMessage covert(RpcRequest rpcRequest) {
        String jsonStr = JSON.toJSONString(rpcRequest);
        byte[] bytes = jsonStr.getBytes(CharsetUtil.UTF_8);
        RpcMessage rpcMessage = new RpcMessage()
                .setContent(bytes)
                .setLength(bytes.length);
        return rpcMessage;
    }
}
