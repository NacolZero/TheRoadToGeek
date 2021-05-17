package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import java.util.Arrays;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 可根据不同 ServerCode 给每次请求配置不同的入站 FilterSet
 */
public class InboudFilterConfig {

    public static InFilterSet initFilters(String serverCode) {
        InFilterSet inFilterSet;
        switch (serverCode) {
            default:
                inFilterSet = new InFilterSet(Arrays.asList(new InboundHeaderFilter(serverCode)));
        }
        return inFilterSet;
    }

}
