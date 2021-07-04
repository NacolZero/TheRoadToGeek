package io.nacol.rpc.api;

import lombok.Data;

@Data
public class RpcResponse {

    /**
     * 返回内容
     */
    private Object result;

    /**
     * 返回状态
     */
    private boolean status;

    /**
     * 异常
     */
    private Exception exception;
}
