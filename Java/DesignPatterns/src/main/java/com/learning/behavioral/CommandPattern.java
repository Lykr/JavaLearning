package com.learning.behavioral;

/**
 * 将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
 * 这样两者之间通过命令对象进行沟通，这样方便将命令对象进行储存、传递、调用、增加与管理。
 */
public class CommandPattern {
    public static void main(String[] args) {
        Invoker invoker = new Invoker(new ConcreteCommandA());
        invoker.call();
        invoker.setCommand(new ConcreteCommandB());
        invoker.call();
    }
}

class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Invoker(Command command) {
        this.command = command;
    }

    public void call() {
        command.execute();
    }
}

interface Command {
    void execute();
}

class ConcreteCommandA implements Command {
    private ReceiverA receiver;

    public ConcreteCommandA() {
        this.receiver = new ReceiverA();
    }

    public void execute() {
        receiver.action();
    }
}

class ConcreteCommandB implements Command {
    private ReceiverB receiver;

    public ConcreteCommandB() {
        this.receiver = new ReceiverB();
    }

    public void execute() {
        receiver.action();
    }
}

class ReceiverA {
    public void action() {
        System.out.println("ReceiverA action.");
    }
}

class ReceiverB {
    public void action() {
        System.out.println("ReceiverB action.");
    }
}
