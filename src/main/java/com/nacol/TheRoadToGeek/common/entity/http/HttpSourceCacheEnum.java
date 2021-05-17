package com.nacol.TheRoadToGeek.common.entity.http;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description 地址配置，暂用枚举，以后可缓存至 redis
 */
public enum HttpSourceCacheEnum {

    TEST("test", "127.0.0.1", 9989, "test", false, "get", null),
    PAY("pay", "127.0.0.1", 9989, "pay", false, "post", "form"),
    LOGIN("login", "127.0.0.1", 9989, "login", false, "post", "body");

    public String serviceCode;
    public String host;
    public int port;
    public String uri;
    public boolean https;
    public String httpType;
    public String paramType;


    HttpSourceCacheEnum(String serviceCode, String host, int port, String uri,
                        boolean https, String httpType, String paramType) {
        this.serviceCode = serviceCode;
        this.host = host;
        this.port = port;
        this.uri = uri;
        this.https = https;
        this.httpType = httpType;
        this.paramType = paramType;
    }

}
