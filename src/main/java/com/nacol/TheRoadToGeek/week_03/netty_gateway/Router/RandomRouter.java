package com.nacol.TheRoadToGeek.week_03.netty_gateway.Router;

import java.util.List;
import java.util.Random;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/17
 * @Description 随机路由
 */
public class RandomRouter implements HttpRouter{

    @Override
    public String route(List<String> urls) {
        Random r = new Random(System.currentTimeMillis());
        return urls.get(r.nextInt(urls.size()));
    }

}
