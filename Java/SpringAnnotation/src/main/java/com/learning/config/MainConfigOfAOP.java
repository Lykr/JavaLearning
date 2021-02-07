package com.learning.config;


import com.learning.aop.LogAspects;
import com.learning.aop.MathCalculator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;


/**
 * AOP：运用动态代理，在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式
 *
 * 1. 导入 AOP 模块 spring-aspect
 * 2. 定义一个业务逻辑类（MathCalculator）：在业务逻辑运行的时候打印日志
 * 3. 定义一个日志切面类（LogAspects）：切面类里的方法需要动态感知 MathCalculator 运行到哪里
 *   通知方法：
 *     前置通知：logStart - @Before
 *     后置通知：logEnd - @After
 *     返回通知：logReturn - @AfterReturning
 *     异常通知：logException - @ AfterThrowing
 *     环绕通知：动态代理，手动推进目标方法运行（jointPoint.proceed()) - @Around
 * 4. 给切面类的目标方法标注何时何地运行
 * 5. 将切面类和业务逻辑类都加入到容器中
 * 6. 告诉 Spring 哪个是切面类（@Aspect）
 * 7. 给配置类加上 @EnableAspectJAutoProxy 开启基于注解的 AOP 模式
 */

@EnableAspectJAutoProxy //开启基于注解的 AOP 自动代理
@Import({MathCalculator.class, LogAspects.class})
@Configuration
public class MainConfigOfAOP {
}
