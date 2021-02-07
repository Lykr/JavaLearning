package com.learning.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean, ApplicationContextAware {

    ApplicationContext applicationContext;

    public Cat() {
        System.out.println("cat constructor");
    }

    @Override
    public void destroy() throws Exception {

        System.out.println("cat destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat init");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
