package com.learning.basic;

public class LocalVariables {
    public static void main(String[] args) {
        LocalVariables localVariables = new LocalVariables();
        localVariables.test();
    }

    /**
     * 局部变量没有默认值，可以不赋值，但没赋值前不能使用
     */
    public void test() {
        Node node;
        int i;
        //System.out.println(i); //报错
    }

    class Node {}
}
