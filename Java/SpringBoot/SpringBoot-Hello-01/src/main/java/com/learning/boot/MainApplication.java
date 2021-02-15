package com.learning.boot;

import com.learning.boot.bean.Pet;
import com.learning.boot.bean.User;
import com.learning.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication //这是一个 SpringBoot 主程序类，主程序所在的包及其子包下的组件都会被扫描
//@SpringBootApplication("com.learning") 改变扫描的包
public class MainApplication {

    public static void main(String[] args) {
        //1. 返回 IOC 容器
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MainApplication.class, args);

        //2. 查看容器中的组件
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }

        //3. 默认按单例模式生成实例
        Pet pet01 = applicationContext.getBean(Pet.class);
        Pet pet02 = applicationContext.getBean(Pet.class);
        System.out.println(pet01 == pet02); // true

        //4. 配置文件是一个实例，并且默认被动态代理
        MyConfig myConfig = applicationContext.getBean(MyConfig.class);
        System.out.println(myConfig);

        //开启 proxyBeanMethods 时能够保证单实例，关闭后会新建实例
        System.out.println(myConfig.user01());
        System.out.println(myConfig.user01() == myConfig.user01());
    }
}
