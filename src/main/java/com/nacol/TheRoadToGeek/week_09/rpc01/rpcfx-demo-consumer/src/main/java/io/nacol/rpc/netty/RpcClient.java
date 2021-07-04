package io.nacol.rpc.netty;

public interface RpcClient {

    <T> T create(final Class<T> clazz, final String url);

}
