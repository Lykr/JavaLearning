package com.learning.config;

import com.learning.bean.Person;
import com.learning.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//配置类 == 配置文件
@Configuration //告诉 Spring 这是一个配置类
//@ComponentScan(value = "com.learning", excludeFilters = {
//        //按注解来筛选不想加入 IOC 容器的类
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class})
//})
@ComponentScan(value = "com.learning", includeFilters = {
        //@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}), //按注解的类筛选
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
}, useDefaultFilters = false)
//注册多个扫描组件
//@ComponentScans(value = {
//        @ComponentScan(value = "com.learning", excludeFilters = {=
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class})
//}),
//        @ComponentScan(value = "com.learning", excludeFilters = {
//                //按注解来选择加入 IOC 容器的类
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})
//        })
//})
public class MainConfig {

    @Bean("person") //给容器中注册一个 Bean，类型为返回值类型，id 默认是方法名，可以自行指定
    public Person person() {
        return new Person("李四", 20 );
    }
}
