package io.nacol.rpc.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/1
 * @Description Rpc 信息解码器
 */
@Log4j2
public class RpcDeconder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf inByteBuf, List<Object> outList) throws Exception {
        log.info("netty rpc decoder is running.");
        if (inByteBuf.readableBytes() > 4) {
            length = inByteBuf.readInt();
        }
        if (inByteBuf.readableBytes() < length) {
            log.info("readable byets is less, wait.");
        }
        byte[] content = new byte[length];
        if (inByteBuf.readableBytes() >= length) {
            inByteBuf.readBytes(content);
            RpcMessage rpcMessage = new RpcMessage();
            rpcMessage.setLength(length);
            rpcMessage.setContent(content);
            outList.add(rpcMessage);
        }
        length = 0;
    }

}
