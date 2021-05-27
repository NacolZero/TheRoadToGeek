package com.nacol.TheRoadToGeek.week_05.other.init_bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean-init")
public class BeanInitController {

    @Autowired
    BeanInitConfig beanInitConfig;

    @GetMapping("/beanInit")
    public String beanInit() {
        System.out.println(beanInitConfig.getBeanName());
        System.out.println(beanInitConfig.getBeanFactory());
        System.out.println(beanInitConfig.getApplicationContext());
        return "ok";
    }

}
