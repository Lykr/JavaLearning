package com.learning.springboot;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;

@DisplayName("测试 Junit5")
//@SpringBootTest //如果需要在测试类中使用 Spring IOC 容器，需要加上这个注解
public class Junit5Test {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void testParameterized(int i) {
        System.out.println(i);
    }

    @Test
    void testAssumptions() {
        Assumptions.assumeTrue(true);
        System.out.println("一切正常");
    }

    @Test
    void testSimpleAssertions() {
        int cal = cal(1, 2);
        Assertions.assertEquals(4, cal);
    }

    int cal(int a, int b) {
        return a + b;
    }

    @Test
    void testAutowire() {
        System.out.println(jdbcTemplate);
    }

    @DisplayName("测试 DisplayName 注解")
    @Test
    void testMethod1() {
        System.out.println("测试方法 1");
    }

    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @DisplayName("测试方法 2")
    @Test
    void testMethod2() throws InterruptedException {
        Thread.sleep(600);
        System.out.println("测试方法 2");
    }

    @Disabled //使该测试失效
    @Test
    void testMethod3() {
        System.out.println("测试方法 3");
    }

    @RepeatedTest(5) //重复测试该方法
    @Test
    void testMethod4() {
        System.out.println("测试方法 4");
    }

    @BeforeEach
    void testBeforeEach() {
        System.out.println("单元测试开始");
    }

    @AfterEach
    void testAfterEach() {
        System.out.println("单元测试结束");
    }

    @BeforeAll
    static void testBeforeAll() {
        System.out.println("所有测试就要开始了");
    }

    @AfterAll
    static void testAfterAll() {
        System.out.println("所有测试都结束了");
    }

}
