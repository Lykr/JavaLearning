package com.learning.basic;

public class StaticBlock {
    public static void main(String[] args) throws Exception {
        Class hello = Class.forName("com.learning.basic.Hello"); // 只要加载了，就会执行静态代码块
        Object o = hello.getDeclaredConstructor().newInstance(); // 初始化后时，先执行非静态代码块，再执行构造方法
    }
}
