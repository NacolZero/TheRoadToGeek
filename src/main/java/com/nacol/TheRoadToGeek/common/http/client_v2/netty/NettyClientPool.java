package com.nacol.TheRoadToGeek.common.http.client_v2.netty;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/6/4
 * @Description
 * client 池
 * 负责缓存各种 client
 * BaseNettyClient 子类不同 host + port 的 client
 */
@Component
public class NettyClientPool {

    /**
     * 因为只会生成一个 map，以及此 map 使用率会相当高，因此使用空间换时间。
     * 因此初始化尽量大，加载因子尽量小，让各个 client 尽量分散。
     */
    private ConcurrentHashMap<String, BaseNettyClient> clientPool = new ConcurrentHashMap<>(128, 0.1f);

    public BaseNettyClient getClient(HttpRequestDto httpRequestDto, Class<? extends BaseNettyClient> clazz) {
        String host = httpRequestDto.getHost();
        int port = httpRequestDto.getPort();
        BaseNettyClient client = clientPool.get(host+port);
        if (client == null) {
            try {
                //TODO 2021.06.04 思考是否用反射，其它方式是否更好
                //FIXME 2021.06.04 其实一个 client 只会被创建一次也不需要考虑性能，暂定反射。
                client = clazz.getConstructor(String.class, String.class)
                        .newInstance(httpRequestDto.getHost(), httpRequestDto.getPort());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
//            client = new AppleNettyClient(httpRequestDto.getHost(), httpRequestDto.getPort());
            //启动client服务
            try {
                client.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            clientPool.put(host+port, client);
        }
        return client;
    }



}
