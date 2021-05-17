package com.nacol.TheRoadToGeek.week_03.netty_gateway.inbound_handler;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.InFilterSet;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.InboudFilterConfig;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.outbound_handler.Exchanger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.log4j.Log4j2;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/11
 * @Description http 对具体业务的处理
 */
@Log4j2
public class InboundHandler extends ChannelInboundHandlerAdapter {

    private String serverCode;
    private Exchanger exchanger;
    private InFilterSet inFilterSet;

    public InboundHandler(String serverCode) {
        this.serverCode = serverCode;
        //初始化转发处理器
        this.exchanger = new Exchanger(serverCode);
        this.inFilterSet = InboudFilterConfig.initFilters(serverCode);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // msg 的本身是一个求情的包装类对象
            // FullHttpRequest 由 Netty 实现
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
            String uri = fullHttpRequest.uri();
            //此处 相当于是路由 或者  controller mapping
            log.info("request param : {}", fullHttpRequest.content().toString(CharsetUtil.UTF_8));
            //进站过滤处理
            inFilterSet.batchFilter(fullHttpRequest, ctx);
            //转发给 outboundHandler 分发消息
            exchanger.route(fullHttpRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.info("channelReadComplete");
        ctx.flush();
    }

}
