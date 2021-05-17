package com.nacol.TheRoadToGeek.week_03.netty_gateway.outbound;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.Router.HttpRouter;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.GatewayConfig;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.NamedThreadFactory;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RouterConfig;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.HttpInboundFilter;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.InFilterSet;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.InboudFilterConfig;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound.OutFilterSet;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound.OutboudFilterConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Exchanger extends ChannelOutboundHandlerAdapter {

    private CloseableHttpAsyncClient httpclient;
    private String serverCode;
    private List<String> hosts;
    private ExecutorService proxyService;
    private HttpRouter router;
    private OutFilterSet outFilterSet;

    public Exchanger(String serverCode) {
        System.out.println("-------------------------------HttpOutboundHandler : " + System.currentTimeMillis());
        this.serverCode = serverCode;
        //STEP 初始化 router
        router = RouterConfig.initRouter(serverCode);

        //STEP 初始化出站过滤
        outFilterSet = OutboudFilterConfig.initFilters(serverCode);


        this.hosts = GatewayConfig
                .config.get(serverCode)
                .stream().map(this::formatUrl)
                .collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response,context) -> 6000)
                .build();
        httpclient.start();
    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void route(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        System.out.println("-------------------------------handle : " + System.currentTimeMillis());
        String backendUrl = router.route(this.hosts);
        final String url = backendUrl + fullHttpRequest.uri();
        System.out.println(" - - - - - - - - fuck : " + url);
//        HttpResponseDto responseDto = HttpClientHelper.sendRequest(requestDto);
//        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }


}
