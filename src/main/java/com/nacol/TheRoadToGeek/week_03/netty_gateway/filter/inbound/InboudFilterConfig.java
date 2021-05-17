package com.nacol.TheRoadToGeek.week_03.netty_gateway.filter.inbound;

import java.util.Arrays;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 最小实现
 */
public class InboudFilterConfig {

    public static InFilterSet initFilters(String serverCode) {
        InFilterSet inFilterSet;
        switch (serverCode) {
            //TODO 可扩展：ServerCode 给每次请求配置不同的入站 FilterSet
            default:
                inFilterSet = new InFilterSet(Arrays.asList(new InboundHeaderFilter(serverCode)));
        }
        return inFilterSet;
    }

}
