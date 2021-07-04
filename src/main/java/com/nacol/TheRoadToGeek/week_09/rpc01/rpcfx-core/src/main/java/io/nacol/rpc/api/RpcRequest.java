package io.nacol.rpc.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RpcRequest {

  /**
   * 接口类名
   */
  private String serviceClass;

  /**
   * 方法名
   */
  private String method;

  /**
   * 参数名称
   */
  private Object[] params;

  /**
   * url
   */
  private String url;
}
