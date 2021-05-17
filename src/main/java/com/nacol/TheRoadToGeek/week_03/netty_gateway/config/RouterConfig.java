package com.nacol.TheRoadToGeek.week_03.netty_gateway.config;

import com.nacol.TheRoadToGeek.week_03.netty_gateway.Router.HttpRouter;
import com.nacol.TheRoadToGeek.week_03.netty_gateway.Router.RandomRouter;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description  router 配置最小实现
 * ps: 可获取 redis 中的配置
 */
public class RouterConfig {

    public static HttpRouter initRouter(String serverCode) {
        HttpRouter router;
        switch (serverCode) {
            default:
                router = new RandomRouter();
        }
        return router;
    }

}
