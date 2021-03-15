package com.learning.creating;

/**
 * 抽象工厂（AbstractFactory）模式的定义：是一种为访问类提供一个创建一组相关或相互依赖对象的接口，
 * 且访问类无须指定所要产品的具体类就能得到同族的不同等级的产品的模式结构。
 *
 * 抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。
 */
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        AbstractFactory factoryA = new ConcreteFactoryA();
        AbstractFactory factoryB = new ConcreteFactoryB();
        Product1 productA1 = factoryA.makeProduct1();
        Product2 productA2 = factoryA.makeProduct2();
        Product1 productB1 = factoryB.makeProduct1();
        Product2 productB2 = factoryB.makeProduct2();
        productA1.show();
        productA2.show();
        productB1.show();
        productB2.show();
    }
}

interface Product1 {
    void show();
}

interface Product2 {
    void show();
}

class ConcreteProdcutA1 implements Product1 {
    @Override
    public void show() {
        System.out.println("产品 A1 正在显示");
    }
}

class ConcreteProdcutA2 implements Product2 {
    @Override
    public void show() {
        System.out.println("产品 A2 正在显示");
    }
}

class ConcreteProdcutB1 implements Product1 {
    @Override
    public void show() {
        System.out.println("产品 B1 正在显示");
    }
}

class ConcreteProdcutB2 implements Product2 {
    @Override
    public void show() {
        System.out.println("产品 B2 正在显示");
    }
}

interface AbstractFactory {
    Product1 makeProduct1();
    Product2 makeProduct2();
}

class ConcreteFactoryA implements AbstractFactory {
    @Override
    public Product1 makeProduct1() {
        System.out.println("生成产品 A1");
        return new ConcreteProdcutA1();
    }

    @Override
    public Product2 makeProduct2() {
        System.out.println("生成产品 A2");
        return new ConcreteProdcutA2();
    }
}

class ConcreteFactoryB implements AbstractFactory {
    @Override
    public Product1 makeProduct1() {
        System.out.println("生成产品 B1");
        return new ConcreteProdcutB1();
    }

    @Override
    public Product2 makeProduct2() {
        System.out.println("生成产品 B2");
        return new ConcreteProdcutB2();
    }
}
