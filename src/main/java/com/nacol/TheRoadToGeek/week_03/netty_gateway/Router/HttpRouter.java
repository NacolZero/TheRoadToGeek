package com.nacol.TheRoadToGeek.week_03.netty_gateway.Router;

import java.util.List;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 路由抽象
 */
public interface HttpRouter {

    public String route(List<String> urls);

}
