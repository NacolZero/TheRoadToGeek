package com.nacol.TheRoadToGeek.common.http.client_v2.config;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/6/4
 * @Description 该类配置 <技术名称> 和 <技术实现 bean> 的映射。
 */
public enum HttpClientConfig {

    HTTP_CLIENT("httpclient", "httpClientHelper"),
    NETTY("netty", "nettyClientHelper");

    /**使用技术名称**/
    public String tecName;
    /**放置在 spring 容器中对应策略 bena 的名称**/
    public String beanName;

    HttpClientConfig(String tecName, String beanName) {
        this.tecName = tecName;
        this.beanName = beanName;
    }

    public static String getBeanName(String tecName) {
        for (HttpClientConfig value : values()) {
            if (value.equals(tecName)) {
                return value.beanName;
            }
        }
        throw new RuntimeException("HttpClientConfig 配置错误");
    }
}
