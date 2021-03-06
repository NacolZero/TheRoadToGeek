package com.nacol.TheRoadToGeek.common.entity.http;

import com.nacol.TheRoadToGeek.common.exception.ServiceException;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static com.nacol.TheRoadToGeek.common.http.client_v2.config.HttpClientConfig.HTTP_CLIENT;
import static com.nacol.TheRoadToGeek.common.http.client_v2.config.HttpClientConfig.NETTY;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description http 请求参数
 */
@ToString
@Data
@Accessors(chain = true)
public class HttpRequestDto extends BaseSendDto implements Serializable {

    /**
     * 发送技术：httpclient、netty
     */
    private String technology;

    /**
     * 业务 Code，对应 ip + port + uri
     */
    private String serviceCode;

    private String host;

    private int port;

    private String uri;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 是否使用 https
     */
    private boolean https;

    /**
     * post
     * get
     */
    private String httpType;

    /**
     * 自定义请求头
     */
    private Map<String, String> customHeaders = new HashMap<>();

    /**
     * 请求参数
     * Get
     * params方式，http://ip:port/uri?param1=val1&param2=val2 请使用 Map
     * Post:
     * form，表单请使用 Map
     * body，请使用 body
     * xml， 使用 String 字符串
     */
    private Object param;

    /**
     * get: 不用设置默认 params 方式
     * Post: from / body / xml
     */
    private String paramType;

    private boolean logRecord;

    public HttpRequestDto() {
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public String getParamType() {
        return this.paramType;
    }


    public String getHttpType() {
        return this.httpType;
    }

    public HttpRequestDto setCustomHeaders(Map<String, String> customHeaders) {
        this.customHeaders = customHeaders;
        return this;
    }

    public Map<String, String> getCustomHeaders() {
        return this.customHeaders;
    }

    public String getUrl() {
        return this.url;
    }

    /**
     * @Author Nacol
     * @Email Nacol@sina.com
     * @Date 2021/5/15
     * @Description 初始化请求 url,
     * 枚举不可修改 + 未设置 set 方法 = 保证了 url 的不可修改。
     */
    public HttpRequestDto init() {
        Assert.notNull(this.serviceCode, "serviceCode cannot be empty.");
        for (HttpSourceCacheEnum source : HttpSourceCacheEnum.values()) {
            if (source.serviceCode.equals(this.serviceCode)) {
                if (url == null) {
                    this.host = source.host;
                    this.port = source.port;
                    this.url = "http://" + source.host + ":" + source.port + "/" + source.uri;
                }
                this.uri = source.uri;
                this.https = source.https;
                this.httpType = source.httpType;
                this.paramType = source.paramType;
                return this;
            }
        }
        throw new ServiceException("request configuration error.");
    }

    public boolean isHttps() {
        return https;
    }

    public HttpRequestDto setHttps(boolean https) {
        this.https = https;
        return this;
    }

    public HttpRequestDto setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public Object getParam() {
        return this.param;
    }

    public HttpRequestDto setParam(Object param) {
        this.param = param;
        return this;
    }

    public String getTechnology() {
        return technology;
    }

    public HttpRequestDto sendByHttpClient(){
        this.technology = HTTP_CLIENT.tecName;
        return this;
    }

    public HttpRequestDto sendByNetty(){
        this.technology = NETTY.tecName;
        return this;
    }

    public boolean isLogRecord(){
        return this.logRecord;
    }

    public HttpRequestDto log() {
        this.logRecord = true;
        return this;
    }

    public String getHostAndPort(){
        return this.host + this.port;
    }

    public String getUri() {
        return uri;
    }
}
