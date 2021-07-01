package io.nacol.rpcfx.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    <T> T resolve(Class<T> clazz, String serviceClass);

}
