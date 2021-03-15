package com.learning.behavioral;

/**
 * 定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤。它是一种类行为型模式。
 */
public class Template {
    public static void main(String[] args) {
        TemplateClass templateClass = new ConcreteClass();
        templateClass.templateMethod();
    }
}

abstract class TemplateClass {
    public void templateMethod() {
        method1();
        method2();
    }

    public void method1() {
        System.out.println("method1");
    }

    public abstract void method2();
}

class ConcreteClass extends TemplateClass {
    @Override
    public void method2() {
        System.out.println("method2");
    }
}
