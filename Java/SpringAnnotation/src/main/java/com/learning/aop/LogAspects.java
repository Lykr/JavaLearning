package com.learning.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.learning.aop.MathCalculator.div(int, int))")
    public void pointcut(){};

    @Before("pointcut()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "除法运行。。。参数列表是：{" + Arrays.asList(joinPoint.getArgs()) + "}");
    }

    @After("pointcut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "除法结束。。。");
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法返回。。。运行结果是：{" + result + "}");
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void loException(Exception e) {
        System.out.println("除法异常。。。异常信息：{" + e.toString() + "}");
    }
}
