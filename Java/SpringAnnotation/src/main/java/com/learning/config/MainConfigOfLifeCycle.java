package com.learning.config;

import com.learning.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Bean 的生命周期：
 *   创建 - 初始化 - 销毁
 * 由容器啦管理
 * 可以自定义初始化和销毁方法
 *
 * 1. 指定初始化和销毁方法：
 *   指定 init-method 和 destroy-method destroy
 * 2. 通过让 Bean 实现 InitializingBean（定义初始化逻辑），DisposableBean（定义销毁逻辑）
 * 3. JSR250：@PostConstruct（在 Bean 初始化完成时执行），@PreDestroy（在销毁前执行）
 * 4. BeanPostProcessor 后置处理器，在 Bean 初始化前后进行一些处理工作
 *   4.1. postProcessorBeforeInitialization 在初始化之前工作
 *   4.2. postProcessorAfterInitialization 在初始化周后工作
 *   4.3. 执行顺序
 *     遍历容器中所有的 BeanPostProcessor，依次执行 beforeInitialization，一旦返回 null，就不会执行后面的 BeanPostProcessor
 *
 *     populateBean(); 给 Bean 赋值
 *     {
 *         applyBeanPostProcessorBeforeInitialization();
 *         invokeInitMethods(); 调用 Bean 的初始化方法
 *         applyBeanPostProcessorBeforeInitialization();
 *     }
 *   4.4. 在 Spring 底层，Bean 赋值、注入其他组件、@Autowired、生命周期注解功能、@Async 都是用 BeanPostProcessor 来实现的
 */

@ComponentScan("com.learning.bean")
@Configuration
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
