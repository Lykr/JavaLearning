package com.learning.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
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
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private int count = 0;

        public void func() {
            lock.lock();
            if (count++ < 3) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": await()");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + ": get signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                condition.signalAll();
            }
            System.out.println(Thread.currentThread().getName() + ": done");
            lock.unlock();
        }
    }
}
