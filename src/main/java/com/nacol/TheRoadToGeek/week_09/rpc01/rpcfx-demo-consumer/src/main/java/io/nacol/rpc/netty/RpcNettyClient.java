package io.nacol.rpc.netty;

import io.nacol.rpc.netty.cache.ProxyCache;
import io.nacol.rpc.netty.proxy.RpcNettyInvocationHandler;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Proxy;

@Service
@Log4j2
public class RpcNettyClient implements RpcClient {

    @Autowired
    ProxyCache proxyCache;

    @Override
    public <T> T create(Class<T> clazz, String url) {
        String clazzName = clazz.getName();
        if (proxyCache.isNotExit(clazzName)) {
            log.info("new proxy");
            T newProxy = (T)Proxy.newProxyInstance(RpcNettyClient.class.getClassLoader(),
                    new Class[]{clazz},
                    new RpcNettyInvocationHandler(clazz, url));
            proxyCache.addProxy(clazzName, newProxy);
            return newProxy;
        } else {
            log.info("use cache proxy");
            return (T)proxyCache.getProxy(clazzName);
        }
    }

}
