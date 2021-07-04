package io.nacol.rpc.netty.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/7/1
 * @Description Rpc 通信数据格式协议
 */
@Data
@Accessors(chain = true)
public class RpcMessage {

    private int length;

    private byte[] content;
}
