package com.learning.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    /**
     * 赋值方式
     *   1. 直接写字符串
     *   2. 用 SpEL 表达式："#{}"
     *   3. 写 "${}" 取出 properties 配置文件中的值（在运行环境变量里的值）
     */
    @Value("张三")
    private String name;
    @Value("#{20-10}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
