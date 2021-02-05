package com.learning.creating;

/**
 * "工厂方法模式"是对简单工厂模式的进一步抽象化，其好处是可以使系统在不修改原来代码的情况下引进新的产品，即满足开闭原则。
 * 工厂方法模式由抽象工厂、具体工厂、抽象产品和具体产品等 4 个要素构成。
 */
public class FactoryMethod {

    public static void main(String[] args) {
        AbstractFactory factoryA = new ConcreteFactoryA();
        AbstractFactory factoryB = new ConcreteFactoryB();
        Product productA = factoryA.makeProduct();
        Product productB = factoryB.makeProduct();
        productA.show();
        productB.show();
    }

    interface Product {
        void show();
    }

    static class ConcreteProdcutA implements Product {
        @Override
        public void show() {
            System.out.println("产品 A 正在显示");
        }
    }

    static class ConcreteProdcutB implements Product {
        @Override
        public void show() {
            System.out.println("产品 B 正在显示");
        }
    }

    interface AbstractFactory {
        Product makeProduct();
    }

    static class ConcreteFactoryA implements AbstractFactory {
        @Override
        public Product makeProduct() {
            System.out.println("生成产品 A");
            return new ConcreteProdcutA();
        }
    }

    static class ConcreteFactoryB implements AbstractFactory {
        @Override
        public Product makeProduct() {
            System.out.println("生成产品 B");
            return new ConcreteProdcutB();
        }
    }

}
