package io.nacol.rpc.netty.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProxyCache {

    private ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    public Object getProxy(String className) {
        return cache.get(className);
    }

    public boolean isNotExit(String className) {
        return !cache.containsKey(className);
    }

    public void addProxy(String className, Object proxy) {
        cache.put(className, proxy);
    }

}
