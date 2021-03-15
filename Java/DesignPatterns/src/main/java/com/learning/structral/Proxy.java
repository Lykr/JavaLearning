package com.learning.structral;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 由于某些原因需要给某对象提供一个代理以控制对该对象的访问。这时，访问对象不适合或者不能直接引用目标对象，代理对象作为访问对象和目标对象之间的中介。
 * 根据代理的创建时期，代理模式分为静态代理和动态代理。
 * - 静态：由程序员创建代理类或特定工具自动生成源代码再对其编译，在程序运行前代理类的 .class 文件就已经存在了。
 * - 动态：在程序运行时，运用反射机制动态创建而成
 */
public class Proxy {

    public static void main(String[] args) {
        Subject staticProxy = new StaticProxy();
        staticProxy.print();

        Handler handler = new Handler();
        Subject dynamicProxy = (Subject) java.lang.reflect.Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        dynamicProxy.print();
    }
}

interface Subject {
    void print();
}

class RealSubject implements Subject {

    @Override
    public void print() {
        System.out.println("Hello~");
    }
}

class StaticProxy implements Subject {
    private Subject subject;

    public StaticProxy() {
        subject = new RealSubject();
    }

    @Override
    public void print() {
        preMethod();
        subject.print();
        postMethod();
    }

    private void preMethod() {
        System.out.println("PreMethod~");
    }

    private void postMethod() {
        System.out.println("PostMethod~");
    }
}

class Handler implements InvocationHandler {
    private Object target;

    public Handler() {
        this.target = new RealSubject();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("PreMethod~");
        method.invoke(target, null);
        System.out.println("PostMethod~");
        return null;
    }
}
