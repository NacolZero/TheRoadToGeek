package io.nacol.rpc.netty.cache;

import io.nacol.rpc.api.RpcRequest;
import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/3
 * @Description Netty Client 缓存
 * 如果把 RpcRequest 作为参数，
 * 增强了 RpcRequest 与缓存的耦合，
 * 但是好处在于调用者不用关心是使用 url 缓存，还是其它方式缓存。
 * 如果刚开始系统使用率不高直接使用 url 缓存(也就是说一个 url 只有一个 channel)，
 * 但是以后稍微有点并发一个 url 一个 channel 可能就不适用，
 * 那么可能可以使用 url+className 或者 url+className+method 创建并缓存多个 channel，
 * 那么调用者则可不修改代码，只用修改缓存代码即可，
 * 解耦了调用者和缓存的方式。
 */
public class NettyClientCache {

    private ConcurrentHashMap<String, Channel> channelCache = new ConcurrentHashMap<>();

    public boolean exist(RpcRequest rpcRequest) {
        return channelCache.containsKey(rpcRequest.getUrl());
    }

    public void addCache(RpcRequest rpcRequest, Channel channel) {
        channelCache.put(rpcRequest.getUrl(), channel);
    }

    public Channel getChannel(RpcRequest rpcRequest) {
        return channelCache.get(rpcRequest.getUrl());
    }

    public void removeChannel(RpcRequest rpcRequest){
        channelCache.remove(rpcRequest.getUrl());
    }
}
