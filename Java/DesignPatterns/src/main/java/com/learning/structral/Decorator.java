package com.learning.structral;

/**
 * 装饰器模式，给对象添加新的行为
 */
public class Decorator {

    public static void main(String[] args) {
        FlyDecorator flyCar = new FlyDecorator(new NormalCar());
        flyCar.move();
        flyCar.fly();
    }
}

interface Car {
    void move();
}

class NormalCar implements Car {
    @Override
    public void move() {
        System.out.println("I can move!");
    }
}

abstract class SuperCar implements Car {
    protected Car car;

    public SuperCar(Car car) {
        this.car = car;
    }
}

class FlyDecorator extends SuperCar {
    public FlyDecorator(Car car) {
        super(car);
    }

    @Override
    public void move() {
        car.move();
    }

    public void fly() {
        System.out.println("I can fly, too!");
    }
}
