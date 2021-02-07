package com.learning.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Profile Spring 提供的可以根据当前环境，动态的激活和切换一系列组件的功能
 *   1. 指定组件在哪个环境下才能被注册到容器中，不指定的话，任何环境下都能注册这个组件
 *   2. 加了 @Profile 的组件只有当对应的环境激活的情况下才能注册到容器中，默认是 default 环境
 *   3. 激活方式：
 *     3.1. 使用命令行动态参数：在虚拟机参数位置加载 -Dspring.profiles.active=test
 *     3.2. 配置 IOC 容器时进行使用 setActiveProfiles 进行设置
 */
//@Profile("test") //只有指定环境下类才会生效
@Configuration
public class MainConfigOfProfile {

    @Profile("test")
    @Bean
    public DataSource dataSourceTest() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/learning_ssm");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/learning_ssm");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public DataSource dataSourceProd() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/learning_ssm");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
