package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.outbound;

import java.util.Arrays;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 可根据不同 ServerCode 给每次请求配置不同的出站 FilterSet
 */
public class OutboudFilterConfig {

    public static OutFilterSet initFilters(String serverCode) {
        OutFilterSet outFilterSet;
        switch (serverCode) {
            default:
                outFilterSet = new OutFilterSet(Arrays.asList(new OutboundHeaderFilter(serverCode)));
        }
        return outFilterSet;
    }

}
