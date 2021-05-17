package com.nacol.TheRoadToGeek.week_03.netty_gateway.inbound_handler;

import com.alibaba.fastjson.JSON;
import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
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
            HttpRequestDto request = (HttpRequestDto)msg;
            log.info("gataway get request : {}", JSON.toJSONString(request));
            //进站过滤处理
            inFilterSet.batchFilter(request, ctx);
            //转发给 outboundHandler 分发消息
            exchanger.route(request, ctx);
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
