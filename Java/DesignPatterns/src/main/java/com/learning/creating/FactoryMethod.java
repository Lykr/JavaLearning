package com.learning.creating;

/**
 * "工厂方法模式"是对简单工厂模式的进一步抽象化，其好处是可以使系统在不修改原来代码的情况下引进新的产品，即满足开闭原则。
 * 工厂方法模式由抽象工厂、具体工厂、抽象产品和具体产品等 4 个要素构成。
 * 核心思想：定义抽象的工厂方法，对每一个特定的产品（对象）定义一个实现该接口的工厂方法，通过对类的扩展实现简单工厂方法中对类修改所实现的功能，满足
 * 开闭原则。
 */
public class FactoryMethod {

    public static void main(String[] args) {
        AbstractFactory0 factoryA = new ConcreteFactoryA0();
        AbstractFactory0 factoryB = new ConcreteFactoryB0();
        Product0 productA = factoryA.makeProduct();
        Product0 productB = factoryB.makeProduct();
        productA.show();
        productB.show();
    }
}

interface Product0 {
    void show();
}

class ConcreteProductA implements Product0 {
    @Override
    public void show() {
        System.out.println("产品 A 正在显示");
    }
}

class ConcreteProductB implements Product0 {
    @Override
    public void show() {
        System.out.println("产品 B 正在显示");
    }
}

interface AbstractFactory0 {
    Product0 makeProduct();
}

class ConcreteFactoryA0 implements AbstractFactory0 {
    @Override
    public Product0 makeProduct() {
        System.out.println("生成产品 A");
        return new ConcreteProductA();
    }
}

class ConcreteFactoryB0 implements AbstractFactory0 {
    @Override
    public Product0 makeProduct() {
        System.out.println("生成产品 B");
        return new ConcreteProductB();
    }
}
