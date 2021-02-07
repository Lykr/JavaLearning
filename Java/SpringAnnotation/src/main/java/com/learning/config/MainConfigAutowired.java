package com.learning.config;

import com.learning.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 用依赖注入（DI），完成对 IOC 容器中各个组件的依赖关系赋值
 *
 * 1. @Autowired 自动注入
 *   1.1. 默认优先按照类型去容器中找对应的组件：applicationContext.getBean()，找到就赋值
 *   1.2. 如果找到多个相同类型的组件，再将属性的名称作为组件 id 去容器中查找
 *   1.3. 使用 @Qualifier(id) 指定需要装配的组件 id
 *   1.4. 自动装配默认必须装配成功，没有会报错，可以使用 @Autowired(required=false) 允许空加载
 *   1.5. 利用 @Primary 指定装配时默认首选的 Bean
 *
 * 2. JSR250：@Resource 和 JSR330：@Inject，需要 javax.annotation，javax.inject
 *   2.1. @Resource：和 @Autowired 功能一样，默认按照组件名称进行装配，不支持 @Primary，不支持空加载
 *   2.2. @Inject：和 @Autowired 功能一样，不支持空加载
 *
 * 3. @Autowired 标记的位置
 *   3.1. 方法
 *   3.2. 构造器：如果组件只有一个有参构造器，该 @Autowired 可以省略
 *   3.3. 参数
 *
 * 4. 自定义组件需要使用 Spring 底层的组件
 *   让自定义组件实现 xxxAware 接口，在创建对象的时候胡调用规定的方法注入组件
 *     比如：ApplicationContextAware
 */

@ComponentScan({"com.learning.bean", "com.learning.service", "com.learning.dao", "com.learning.controller"})
@Configuration
public class MainConfigAutowired {

    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
}
