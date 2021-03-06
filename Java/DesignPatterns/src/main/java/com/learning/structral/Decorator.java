package com.learning.structral;

public class Decorator {

    public static void main(String[] args) {
        FlyDecorator flyCar = new FlyDecorator(new NormalCar());
        flyCar.move();
        flyCar.fly();
    }

    interface Car {
        void move();
    }

    static class NormalCar implements Car {
        @Override
        public void move() {
            System.out.println("I can move!");
        }
    }

    static abstract class SuperCar implements Car {
        protected Car car;

        public SuperCar(Car car) {
            this.car = car;
        }
    }

    static class FlyDecorator extends SuperCar {
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
}
