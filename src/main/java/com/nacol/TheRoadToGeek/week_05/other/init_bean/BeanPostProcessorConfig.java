package com.nacol.TheRoadToGeek.week_05.other.init_bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2021/5/26
 * @Description 该类可以预处理我们想处理的 bean 对象
 */
@Component
public class BeanPostProcessorConfig implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        BeanInitConfig beanInitConfig = null;
        if (bean instanceof BeanInitConfig) {
            System.out.println("postProcessBeforeInitialization");
            beanInitConfig = (BeanInitConfig)bean;
            beanInitConfig.setBeanName(beanInitConfig.getBeanName() + "Super");
        }
        return beanInitConfig;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        BeanInitConfig beanInitConfig = null;
        if (bean instanceof BeanInitConfig) {
            System.out.println("postProcessAfterInitialization");
            beanInitConfig = (BeanInitConfig)bean;
            beanInitConfig.setBeanName(beanInitConfig.getBeanName() + "X");
        }
        return beanInitConfig;
    }

}
