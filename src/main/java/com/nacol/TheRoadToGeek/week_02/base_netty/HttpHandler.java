package com.nacol.TheRoadToGeek.week_02_netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static org.apache.coyote.http11.Constants.CONNECTION;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/11
 * @Description http 对具体业务的处理
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("--------------------");
        try {
            // msg 的本身是一个求情的包装类对象
            // FullHttpRequest 由 Netty 实现
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
            String uri = fullHttpRequest.uri();
            System.out.println("uri : " + uri);

            //此处 相当于是路由 或者  controller mapping
            if (uri.contains("/test")) {
                handle(fullHttpRequest, ctx, "Hi ^@^ Test");
            } else {
                handle(fullHttpRequest, ctx, "Hi ^@^ Other");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handle(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, String msg) {
        // Netty 4.1.51 实现
        FullHttpResponse respone = null;
        try {
            respone = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(msg.getBytes()));
            respone.headers().set("Content-Type", "application/json");
            respone.headers().setInt("Content-Length", respone.content().readableBytes());
        } catch (Exception e) {
            System.out.println("处理出错" + e.getMessage());
            respone = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(respone).addListener(ChannelFutureListener.CLOSE);
                    ctx.flush();
                } else {
                    respone.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(respone);
                    ctx.flush();
                }
            }
        }
    }


}
