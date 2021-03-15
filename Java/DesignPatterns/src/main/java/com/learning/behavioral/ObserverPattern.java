package com.learning.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * 指多个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 这种模式有时又称作发布-订阅模式、模型-视图模式，它是对象行为型模式。
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserver1();
        Observer obs2 = new ConcreteObserver2();
        subject.addObserver(obs1);
        subject.addObserver(obs2);
        subject.notifyObserver();
    }
}

abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    public boolean removeObserver(Observer observer) {
        return observers.remove(observer);
    }

    public abstract void notifyObserver();
}

class ConcreteSubject extends Subject {
    @Override
    public void notifyObserver() {
        observers.forEach(Observer::response);
    }
}

interface Observer {
    void response();
}

class ConcreteObserver1 implements Observer {
    @Override
    public void response() {
        System.out.println("Observer1 got notify!");
    }
}

class ConcreteObserver2 implements Observer {
    @Override
    public void response() {
        System.out.println("Observer2 got notify!");
    }
}
