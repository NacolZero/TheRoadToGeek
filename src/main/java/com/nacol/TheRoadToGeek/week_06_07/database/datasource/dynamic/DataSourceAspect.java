package com.nacol.TheRoadToGeek.week_06_07.database.datasource.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.nacol.TheRoadToGeek.week_06_07.database.datasource.base.DataSourceNameConfig.HIKARI_MYSQL;

/**
 * 多数据源，切面处理类
 *
 * @author xiaohe
 * @version V1.0.0
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.nacol.TheRoadToGeek.week_06_07.database.datasource.dynamic.DataSourceConfig)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSourceConfig ds = method.getAnnotation(DataSourceConfig.class);
        if (ds == null) {
            DynamicDataSource.setDataSource(HIKARI_MYSQL);
            log.info("set datasource is " + HIKARI_MYSQL);
        } else {
            DynamicDataSource.setDataSource(ds.name());
            log.info("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            log.info("clean datasource");
        }
    }

}
