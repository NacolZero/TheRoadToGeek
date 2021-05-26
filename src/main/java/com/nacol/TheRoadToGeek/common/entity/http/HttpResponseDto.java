package com.nacol.TheRoadToGeek.common.entity.http;


import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nacol  
 * @Email Nacol@sina.com
 * @Date 2021/5/15  
 * @Description http 返回
 */
@ToString
public class HttpResponseDto extends BaseSendDto implements Serializable {

    private int status;

    private Object resultData;

    private String serviceCode;

    private Map<String, String> customHeaders = new HashMap<>();

    public Object getResultData() {
        return resultData;
    }

    public HttpResponseDto setResultData(Object resultData) {
        this.resultData = resultData;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public HttpResponseDto setStatus(int status) {
        this.status = status;
        return this;
    }

    public Map<String, String> getCustomHeaders() {
        return customHeaders;
    }

    public void setCustomHeaders(Map<String, String> customHeaders) {
        this.customHeaders = customHeaders;
    }

    public HttpResponseDto setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public String getServiceCode() {
        return this.serviceCode;
    }

}
