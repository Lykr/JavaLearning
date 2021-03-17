package com.learning.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        TestClass testClass0 = new TestClass();
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        threadPool.execute(testClass::func);
        threadPool.execute(testClass0::func);
        threadPool.shutdown();
    }

    static class TestClass {
        private static ReentrantLock staticLock = new ReentrantLock();
        private ReentrantLock instanceLock = new ReentrantLock();

        public void func() {
            instanceLock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            instanceLock.unlock();
        }
    }
}
