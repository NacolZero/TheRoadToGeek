package com.nacol.TheRoadToGeek.common.http.param_strategy;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.http.param_strategy.impl.BodyParam;
import com.nacol.TheRoadToGeek.common.http.param_strategy.impl.FormParam;
import com.nacol.TheRoadToGeek.common.http.param_strategy.impl.GetParam;
import com.nacol.TheRoadToGeek.common.http.param_strategy.impl.XmlParam;

import java.util.HashMap;
import java.util.Map;

import static com.nacol.TheRoadToGeek.common.entity.http.HttpConstants.*;


/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/16
 * @Description 请求参数策略工厂类
 */
public class ParamStrategyFactory {

    public static final Map<String, ParamStrategy> STRATEGY_MAP = new HashMap<>();

    static {
        STRATEGY_MAP.put(PARAM_BODY, new BodyParam());
        STRATEGY_MAP.put(PARAM_FORM, new FormParam());
        STRATEGY_MAP.put(HTTP_GET, new GetParam());
        STRATEGY_MAP.put(PARAM_XML, new XmlParam());
    }

    public static ParamStrategy getParamStrategy(HttpRequestDto httpRequestDto) {
        String paramType;
        //GET
        if (HTTP_GET.equals(httpRequestDto.getHttpType())) {
            paramType = httpRequestDto.getHttpType();
        }
        //POST
        else {
            paramType = httpRequestDto.getParamType();
        }
        return STRATEGY_MAP.get(paramType);
    }
}
