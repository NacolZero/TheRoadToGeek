package com.nacol.TheRoadToGeek.week_05.other.init_bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/26
 * @Description 该类存储了 BeanFactory 和 applicationContext，并可修改 beanName
 * 则可以拿 beanFactory 和 Bean 容器搞很多事情。
 */
@Data
@Component
public class BeanInitConfig implements Serializable, ApplicationContextAware, BeanFactoryAware, BeanNameAware {

    private String beanName;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
