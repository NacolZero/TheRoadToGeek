package com.nacol.TheRoadToGeek.week_05.other.init_bean;

import com.nacol.TheRoadToGeek.common.entity.http.HttpSource;
import com.nacol.TheRoadToGeek.common.springutils.SpringBeanPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean-init")
public class BeanInitController {

    @Autowired
    BeanInitConfig beanInitConfig;

    @Autowired
    SpringBeanPool springBeanPool;

    @GetMapping("/beanInit")
    public String beanInit() {
        System.out.println(beanInitConfig.getBeanName());
        System.out.println(beanInitConfig.getBeanFactory());
        System.out.println(beanInitConfig.getApplicationContext());
        return "ok";
    }

    @GetMapping("/getStarterBean")
    public String getStarterBean() {
        HttpSource httpSource = springBeanPool.getBean("loginHttpSouce", HttpSource.class);
        System.out.println(httpSource);
        return "ok";
    }

}
