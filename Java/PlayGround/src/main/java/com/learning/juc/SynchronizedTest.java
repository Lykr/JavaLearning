package com.learning.juc;

import java.util.concurrent.*;

public class SynchronizedTest {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        threadPool.execute(() -> testClass.func());
        threadPool.execute(() -> testClass.func());
        threadPool.shutdown();
    }

    static class TestClass {
        public synchronized void func() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    }
}
