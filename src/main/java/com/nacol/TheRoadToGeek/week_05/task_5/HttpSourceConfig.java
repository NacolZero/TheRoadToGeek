package com.nacol.TheRoadToGeek.week_05.task_5;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "http.source")
public class HttpSourceConfig {

    private String serviceCode;
    private String ip;
    private String port;
    private String uri;
    private boolean https;
    private String httpType;
    private String paramType;

}
