package io.nacol.rpc.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.log4j.Log4j2;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/1
 * @Description RPC 信息编码器
 */
@Log4j2
public class RpcEncoder extends MessageToByteEncoder<RpcMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage, ByteBuf outByteBuf) throws Exception {
        log.info("netty rpc encoder is running.");
        outByteBuf.writeInt(rpcMessage.getLength());
        outByteBuf.writeBytes(rpcMessage.getContent());
    }
}
