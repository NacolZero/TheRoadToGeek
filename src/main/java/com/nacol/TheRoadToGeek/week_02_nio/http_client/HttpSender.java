package com.nacol.TheRoadToGeek.week_02_nio.http_client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpSender {

    public static void main(String[] args) throws IOException {
        //Low B 版本
        String requestAddress = "http://127.0.0.1:9989/test";
        HttpUriRequest requestBase = new HttpGet(requestAddress);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(requestBase);
        System.out.println(EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8));
        httpClient.close();
        httpResponse.close();
    }


}
