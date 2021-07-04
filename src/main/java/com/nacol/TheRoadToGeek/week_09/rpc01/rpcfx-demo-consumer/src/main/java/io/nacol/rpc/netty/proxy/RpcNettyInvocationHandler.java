package io.nacol.rpc.netty.proxy;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.nacol.rpc.api.RpcRequest;
import io.nacol.rpc.api.RpcResponse;
import io.nacol.rpc.netty.Factory.NettyClientSingleFactory;
import lombok.extern.log4j.Log4j2;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

@Log4j2
public class RpcNettyInvocationHandler implements InvocationHandler
//        , MethodInterceptor
                {

    private final Class<?> serviceClass;
    private final String url;

    public <T> RpcNettyInvocationHandler(Class<T> serviceClass, String url) {
        this.serviceClass = serviceClass;
        this.url = url;
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        log.info("invoke");
        return process(serviceClass, method, args, url);
    }

//    @Override
//    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) {
//        log.info("intercept");
//        return process(serviceClass, method, args, url);
//    }

    private Object process(Class<?> serviceClass, Method method, Object[] args, String url) {
        log.info("netty invoke start");
        RpcRequest rpcRequest = new RpcRequest()
                .setUrl(url)
                .setServiceClass(serviceClass.getName())
                .setMethod(method.getName())
                .setParams(args);
        RpcResponse rpcResponse = null;
        try {
            rpcResponse = NettyClientSingleFactory.getClient().request(rpcRequest);
        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }

        if (!rpcResponse.isStatus()) {
            rpcResponse.getException().printStackTrace();
            return null;
        }
        return JSON.parse(rpcResponse.getResult().toString());
    }
}