package com.learning.config;

import com.learning.bean.Color;
import com.learning.bean.ColorFactory;
import com.learning.bean.Person;
import com.learning.bean.Red;
import com.learning.condition.*;
import org.springframework.context.annotation.*;

//@Conditional({WindowsCondition.class}) //满足条件时类才能生效
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) //快速加载，id 默认为全类名
public class MainConfig2 {

    //prototype：多实例，启动时不创建，调用时创建实例
    //singleton：单实例（默认），启动时创建实例（饿汉式单例）
    //web 环境下，request：同一次请求创建一个实例，session：同一个 session 创建一个实例
    //@Scope("prototype")
    @Lazy //单例模式下开启懒加载（IOC 容器启动时不自动创建实例，调用时再创建）
    @Bean("person")
    public Person person() {
        System.out.println("给 IOC 容器添加 Person");
        return new Person("张三", 25);
    }

    //注册组件的三种方法：
    //1. 包扫描 + 注释（@Controller、……、@Repository）
    //2. @Bean
    //3. @Import
    //  3.1. @Import({类, ... ,类})
    //  3.2. @Import(ImportSelector.class)，ImportSelector 中返回需要导入的类的全类名
    //  3.3. ImportBeanDefinitionRegistrar，手动注册 Bean 到容器中
    //4. 使用 Spring 提供的 FactoryBean
    //  4.1. 默认获取的是工厂 Bean 调用 getObject 创建的对象
    //  4.2. 如果想要获取工厂 Bean 本身，需要在 id 前加 &

    @Conditional({WindowsCondition.class}) //满足条件才会加载
    @Bean("Bill")
    public Person person01() {
        return new Person("比尔盖茨", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("Linus")
    public Person person02() {
        return new Person("莱纳斯", 48);
    }

    @Conditional({MacCondition.class})
    @Bean("Jobs")
    public Person person03() {
        return new Person("乔布斯", 56);
    }

    /**
     * 加载 ColorFactory 类中 getObject 方法的返回值
     * @return
     */
    @Bean
    public ColorFactory colorFactory() {
        return new ColorFactory();
    }

}
