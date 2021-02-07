package com.learning.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        //获取 bean 定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        //获取环境信息
        Environment environment = context.getEnvironment();

        boolean bool = registry.containsBeanDefinition("person");

        String property = environment.getProperty("os.name");

        if (property.contains("Linux") || bool) return true;
        return false;
    }
}
