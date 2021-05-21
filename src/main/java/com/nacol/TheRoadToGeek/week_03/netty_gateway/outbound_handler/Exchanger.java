package com.nacol.TheRoadToGeek.week_03.netty_gateway.outbound_handler;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.client.httpclient.HttpClientHelper;
import com.nacol.TheRoadToGeek.common.http.client.httpclient.HttpClientHelper2;
import com.nacol.TheRoadToGeek.common.http.client.nettyclient.NettyClientHelper;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.Router.HttpRouter;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.GatewayConfig;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.NamedThreadFactory;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.config.RouterConfig;
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

    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }

    public void route(HttpRequestDto fullHttpRequest, ChannelHandlerContext ctx) {
        //负债均衡
        String backendUrl = router.route(this.hosts);
        final String url = backendUrl + "/" +fullHttpRequest.getUri();
        //这里转发给其他服务即可
        fullHttpRequest.setUrl(url);
        HttpClientHelper2.sendRequest(fullHttpRequest);
    }


}
