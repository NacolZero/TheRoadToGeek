package com.nacol.TheRoadToGeek.week_05.task_8;


import com.nacol.TheRoadToGeek.common.entity.http.HttpSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HttpSourceConfig.class)
public class HttpSouceAutoConfiguration {

    @Bean
    public HttpSource loginHttpSouce(HttpSourceConfig httpSourceConfig) {
        System.out.println("---------------------开始装配loginHttpSouce");
        //使用构建者模式创建对象，保证对象不在使用过程中修改。
        return new HttpSource.Builder()
                .setHttps(httpSourceConfig.isHttps())
                .setHttpType(httpSourceConfig.getHttpType())
                .setIp(httpSourceConfig.getIp())
                .setParamTyper(httpSourceConfig.getParamType())
                .setPort(httpSourceConfig.getPort())
                .setServiceCode(httpSourceConfig.getServiceCode())
                .setUri(httpSourceConfig.getUri())
                .build();

    }

}
