package com.nacol.TheRoadToGeek.common.entity.http;


import com.alibaba.fastjson.JSONObject;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author Nacol  
 * @Email Nacol@sina.com
 * @Date 2021/5/15  
 * @Description http 返回
 */
public class HttpResponseDto extends BaseSendDto implements Serializable {
    
    private JSONObject resultData;

    public JSONObject getResultData() {
        return resultData;
    }

    public void setResultData(JSONObject resultData) {
        this.resultData = resultData;
    }
}
