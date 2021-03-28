package com.learning.behavioral;

/**
 * 该模式定义了一系列算法，并将每个算法封装起来，使它们可以相互替换，且算法的变化不会影响使用算法的客户。
 * 策略模式属于对象行为模式，它通过对算法进行封装，把使用算法的责任和算法的实现分割开来，并委派给不同的对象对这些算法进行管理。
 * 和命令模式中的命令比较相似。
 */
public class Strategy {
    public static void main(String[] args) {
        StrategyInterface strategy = new StrategyImplementA();
        strategy.method();
        strategy = new StrategyImplementB();
        strategy.method();
    }
}

interface StrategyInterface {
    void method();
}

class StrategyImplementA implements StrategyInterface {
    @Override
    public void method() {
        System.out.println("StrategyImplementA");
    }
}

class StrategyImplementB implements StrategyInterface {
    @Override
    public void method() {
        System.out.println("StrategyImplementB");
    }
}
