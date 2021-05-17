package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import java.util.Arrays;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 最小实现
 */
public class OutboudFilterConfig {

    public static OutFilterSet initFilters(String serverCode) {
        OutFilterSet outFilterSet;
        switch (serverCode) {
            //TODO 可扩展：ServerCode 给每次请求配置不同的入站 FilterSet
            default:
                outFilterSet = new OutFilterSet(Arrays.asList(new OutboundHeaderFilter(serverCode)));
        }
        return outFilterSet;
    }

}
