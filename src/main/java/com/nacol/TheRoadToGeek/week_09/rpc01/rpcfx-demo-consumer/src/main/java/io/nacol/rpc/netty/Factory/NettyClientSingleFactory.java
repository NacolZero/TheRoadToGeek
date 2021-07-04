package io.nacol.rpc.netty.Factory;

import io.nacol.rpc.netty.RpcSyncNettyClient;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/3
 * @Description Netty client 单例工厂
 */
public enum NettyClientSingleFactory {

    CLIENT(new RpcSyncNettyClient());

    private NettyClientSingleFactory(RpcSyncNettyClient rpcSyncNettyClient) {
        this.rpcSyncNettyClient = rpcSyncNettyClient;
    }

    private RpcSyncNettyClient rpcSyncNettyClient;

    public static RpcSyncNettyClient getClient() {
        return CLIENT.rpcSyncNettyClient;
    }
}
