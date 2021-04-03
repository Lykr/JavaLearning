package com.learning.creating;

/**
 * 定义一个创建产品对象的工厂接口，将产品对象的实际创建工作推迟到具体子工厂类当中。
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Product A = Factory.getProduct(ProductKind.A);
        Product B = Factory.getProduct(ProductKind.B);
        A.show();
        B.show();
    }

    public interface Product {
        void show();
    }

    public enum ProductKind {
        A, B
    }

    static class ConcreteProductA implements Product {
        @Override
        public void show() {
            System.out.println("产品 A 正在显示");
        }
    }

    static class ConcreteProductB implements Product {
        @Override
        public void show() {
            System.out.println("产品 B 正在显示");
        }
    }

    //简单工厂模式每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度，违背了“开闭原则”。
    static class Factory {
        public static Product getProduct(ProductKind kind) {
            switch (kind) {
                case A:
                    System.out.println("生成产品 A");
                    return new ConcreteProductA();
                case B:
                    System.out.println("生成产品 B");
                    return new ConcreteProductB();
            }
            return null;
        }
    }
}
