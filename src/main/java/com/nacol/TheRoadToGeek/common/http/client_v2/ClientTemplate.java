package com.nacol.TheRoadToGeek.common.http.client_v2;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.common.http.client_v2.config.HttpClientConfig;
import com.nacol.TheRoadToGeek.common.http.client_v2.helper.IHttpClient;
import com.nacol.TheRoadToGeek.common.http.gateway.annotation.RequestEnhance;
import com.nacol.TheRoadToGeek.common.springutils.SpringBeanPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/6/4
 * @Description
 * Client 模板
 * 目前负责获取对应技术实现以及发送请求。
 * 此处也可作为所有不同技术相同业务的扩展点。比如日志。
 */
@Component
public class ClientTemplate {

    @Autowired
    SpringBeanPool springBeanPool;

    @RequestEnhance
    public HttpResponseDto sendRequest(HttpRequestDto httpRequestDto) {
        //STEP 获取具体实现
        String beanName = HttpClientConfig.getBeanName(httpRequestDto.getTechnology());
        IHttpClient client = springBeanPool.getBean(beanName, IHttpClient.class);

        //STEP 发送请求
        HttpResponseDto resp = client.sendRequest(httpRequestDto);
        return resp;
    }

}
