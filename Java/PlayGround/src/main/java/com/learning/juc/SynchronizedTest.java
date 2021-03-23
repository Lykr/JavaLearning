package com.learning.juc;

import java.util.concurrent.*;

public class SynchronizedTest {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        threadPool.execute(testClass::func1);
        threadPool.execute(testClass::func2);
        threadPool.shutdown();
    }

    static class TestClass {
        // Synchronized mark a function equals to synchronized (this)
        public synchronized void func1() {
            System.out.println("Enter func1");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            System.out.println("Exit func1");
        }

        public synchronized void func2() {
            System.out.println("Enter func2");
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
            System.out.println("Exit func2");
        }
    }
}
