package com.learning.behavioral;

/**
 * 为了避免请求发送者与多个请求处理者耦合在一起，于是将所有请求的处理者通过前一对象记住其下一个对象的引用而连成一条链；
 * 当有请求发生时，可将请求沿着这条链传递，直到有对象处理它为止。
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler handler = new ConcreteHandler1();
        handler.setNext(new ConcreteHandler2());
        handler.handleRequest("Hello World!");
    }
}

abstract class Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    abstract void handleRequest(String request);
}

class ConcreteHandler1 extends Handler {
    @Override
    void handleRequest(String request) {
        System.out.println("ConcreteHandler1 do something for " + request);
        if (getNext() != null) getNext().handleRequest(request);
    }
}

class ConcreteHandler2 extends Handler {
    @Override
    void handleRequest(String request) {
        System.out.println("ConcreteHandler2 do something for " + request);
        if (getNext() != null) getNext().handleRequest(request);
    }
}
