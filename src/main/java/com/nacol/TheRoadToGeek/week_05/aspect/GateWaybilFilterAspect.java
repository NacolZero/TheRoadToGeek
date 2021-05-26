package com.nacol.TheRoadToGeek.week_05.aspect;

import com.nacol.TheRoadToGeek.common.entity.http.HttpRequestDto;
import com.nacol.TheRoadToGeek.common.entity.http.HttpResponseDto;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.InFilterSet;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound.InboudFilterConfig;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound.OutFilterSet;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound.OutboudFilterConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GateWaybilFilterAspect {

    @Pointcut("@annotation(com.nacol.TheRoadToGeek.common.annotation.HttpFilter)")
    public void httpFilter(){}

    @Before("httpFilter()")
    public void doBebore(JoinPoint joinPoint) throws Exception{
        //STEP 获取请求
        HttpRequestDto request = getRequest(joinPoint);
        if (request == null) {
            return;
        }
        //STEP 获取请求对应 serviceCode 的过滤器集合
        InFilterSet inFilterSet = InboudFilterConfig.initFilters(request.getServiceCode());
        //STEP 执行请求过滤
        inFilterSet.batchFilter(request, null);
    }

    @AfterReturning(returning = "params",pointcut = "httpFilter()")
    public void doAfterReturning(JoinPoint joinPoint, Object params) throws Exception{
        //STEP 获取返回
        if (!(params instanceof HttpResponseDto)) {
            return;
        }
        HttpResponseDto response = (HttpResponseDto)params;
        //STEP 获取返回对应 serviceCode 的过滤器集合
        OutFilterSet outFilterSet = OutboudFilterConfig.initFilters(response.getServiceCode());
        //STEP 执行返回过滤
        outFilterSet.batchFilter(response, null);
    }


    private HttpRequestDto getRequest(JoinPoint joinPoint) {
        HttpRequestDto request = null;
        for (Object obj : joinPoint.getArgs()) {
            if (obj instanceof HttpRequestDto) {
                request = (HttpRequestDto)obj;
            }
        }
        return request;
    }
}
