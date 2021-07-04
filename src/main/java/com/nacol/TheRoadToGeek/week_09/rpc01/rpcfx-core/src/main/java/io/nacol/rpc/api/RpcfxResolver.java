package io.nacol.rpc.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    <T> T resolve(Class<T> clazz, String serviceClass);

}
