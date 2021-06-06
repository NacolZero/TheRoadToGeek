package com.nacol.TheRoadToGeek.common.entity.http;

import lombok.ToString;
import org.springframework.util.Assert;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/15
 * @Description
 * 1. 当访问外部服务时或者被外部访问时，不应随意填写 ip、port、uri, 应该作为配置缓存起来，每次访问需要从缓存中取；
 * 2. 为了防止从缓存取出后不被修改，使用 Builder 模式。(可直接从 Redis 以对象的形式取出来)
 * ps：当前使用了枚举使用不到该类，待使用 Redis 后可使用该类代替枚举，并重写 HttpResponseDto.initUr()。
 */
@ToString
public class HttpSource {

    private String serviceCode;
    private String ip;
    private String port;
    private String uri;
    private boolean https;
    private String httpType;
    private String paramType;


    private HttpSource(Builder builder) {
        this.serviceCode = builder.serviceCode;
        this.ip = builder.ip;
        this.port = builder.port;
        this.uri = builder.uri;
        this.https = builder.https;
        this.httpType = builder.httpType;
        this.paramType = builder.paramType;
    }

    public static class Builder {
        private String serviceCode;
        private String ip;
        private String port;
        private String uri;
        private boolean https;
        private String httpType;
        private String paramType;

        public HttpSource build() {
            Assert.notNull(this.serviceCode, "serviceCode cannot be empty");
            Assert.notNull(this.ip, "ip cannot be empty");
            Assert.notNull(this.port, "port cannot be empty");
            Assert.notNull(this.uri, "uri cannot be empty");
            Assert.notNull(this.https, "https cannot be empty");
            Assert.notNull(this.httpType, "httpType cannot be empty");
            Assert.notNull(this.paramType, "paramType cannot be empty");
            return new HttpSource(this);
        }

        public Builder setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder setPort(String port) {
            this.port = port;
            return this;
        }

        public Builder setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder setHttps(boolean https) {
            this.https = https;
            return this;
        }

        public Builder setHttpType(String httpType){
            this.httpType = httpType;
            return this;
        }

        public Builder setParamTyper(String paramType) {
            this.paramType = paramType;
            return this;
        }
    }

    public String getIp() {
        return this.ip;
    }

    public String getPort() {
        return this.port;
    }

    public String getUri() {
        return this.uri;
    }
}
