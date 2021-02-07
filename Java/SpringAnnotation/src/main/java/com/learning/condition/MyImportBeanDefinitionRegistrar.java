package com.learning.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements org.springframework.context.annotation.ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 被注解类的信息
     * @param registry 注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean color = registry.containsBeanDefinition("com.learning.bean.Color");
        if (color) {
            registry.registerBeanDefinition("black", new RootBeanDefinition("com.learning.bean.Black"));
        }
    }
}
