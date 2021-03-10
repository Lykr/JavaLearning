package com.learning.juc;

import java.net.InetAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

    public static void main(String[] args) {
        int threadNum = 1000;
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Test test = new Test();

        for (int i = 0; i < threadNum; i++) {
            threadPool.execute(() -> {
                System.out.println(test.add());
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.get());
        threadPool.shutdown();
    }

    static class Test {
        private volatile int cnt = 0; // volatile 不保证操作的原子性

        public int add() {
            return ++cnt;
        }

        public int get() {
            return cnt;
        }
    }
}
