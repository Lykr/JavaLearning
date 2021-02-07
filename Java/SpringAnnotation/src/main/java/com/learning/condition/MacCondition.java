package com.learning.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {

    /**
     *
     * @param context 判断条件能使用的上下文（环境）
     * @param metadata 注解信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取 IOC 使用的 BeanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取 bean 定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        //获取环境信息
        Environment environment = context.getEnvironment();

        String property = environment.getProperty("os.name");
        if (property.contains("Mac")) return true;
        return false;
    }
}
