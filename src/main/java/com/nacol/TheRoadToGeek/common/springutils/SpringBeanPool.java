package com.nacol.TheRoadToGeek.common.springutils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanPool implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public <T> T getBean(String beanName, Class<T> t) {
        return (T)applicationContext.getBean(beanName);
    }

    public <T> T getBean(T t) {
        return (T)applicationContext.getBean(t.getClass());
    }

    public <T> T getBean(String beanName, T t) {
        return (T)applicationContext.getBean(beanName, t.getClass());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
