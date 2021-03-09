package com.learning.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitTest {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        threadPool.execute(() -> testClass.func());
        threadPool.execute(() -> testClass.func());
        threadPool.execute(() -> testClass.func());
        threadPool.execute(() -> testClass.func());
        threadPool.shutdown();
    }

    static class TestClass {
        private int count = 0;

        public synchronized void func() {
            if (count++ < 3) {
                try {
                    wait(); // 挂起当前线程后会释放锁，以便于之后的线程调用该方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                notifyAll();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
