package com.learning.behavioral;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
 * 中介者模式又叫调停模式，它是迪米特法则的典型应用。
 */
public class MediatorPattern {
    public static void main(String[] args) {
        Mediator concreteMediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1();
        Colleague colleague2 = new ConcreteColleague2();
        concreteMediator.register(colleague1);
        concreteMediator.register(colleague2);
        colleague1.send();
        colleague2.send();
    }
}

abstract class Colleague {
    protected Mediator mediator;

    protected void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();

    public abstract void send();
}

class ConcreteColleague1 extends Colleague {
    @Override
    public void receive() {
        System.out.println("ConcreteColleague1 received.");
    }

    @Override
    public void send() {
        System.out.println("ConcreteColleague1 sent.");
        mediator.relay(this);
    }
}

class ConcreteColleague2 extends Colleague {
    @Override
    public void receive() {
        System.out.println("ConcreteColleague2 received.");
    }

    @Override
    public void send() {
        System.out.println("ConcreteColleague2 sent.");
        mediator.relay(this);
    }
}

interface Mediator {
    void register(Colleague colleague);

    void relay(Colleague colleague);
}

class ConcreteMediator implements Mediator {
    private Set<Colleague> set = new HashSet<>();

    @Override
    public void register(Colleague colleague) {
        set.add(colleague);
        colleague.setMediator(this);
    }

    @Override
    public void relay(Colleague colleague) {
        Iterator<Colleague> iterator = set.iterator();
        while (iterator.hasNext()) {
            Colleague next = iterator.next();
            if (!colleague.equals(next)) {
                next.receive();
            }
        }
    }
}
