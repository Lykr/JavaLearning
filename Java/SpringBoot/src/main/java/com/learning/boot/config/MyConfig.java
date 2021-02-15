package com.learning.boot.config;

import com.learning.boot.bean.Car;
import com.learning.boot.bean.Pet;
import com.learning.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration //这是一个配置类，会被当做组件加载进 IOC 容器
//@Configuration(proxyBeanMethods = true) 通过设置 proxyBeanMethods 让该类被代理增强，能够避免通过从容器中获取该类直接生成对象
//@Import({Test.class, ...}) 通过 Import 导入，默认 id 为全类名
//@ImportResource("classpath:beans.xml") 通过 xml 的方式导入 Beans
@EnableConfigurationProperties(Car.class) //开启 Car 的属性配置功能，并自动注册到容器中
public class MyConfig {

    @ConditionalOnBean(name = "tom") //容器中有 id 为 tom 的组件时生效
    @Bean //给容器中添加组件，以方法名作为 id，返回类型是组件类型，返回值是传给容器的实例
    public User user01() {
        return new User("张三", 24, tomcatPet());
    }

    @Bean("tom") //自定义组件 id
    public Pet tomcatPet() {
        return new Pet("Tomcat");
    }

}
