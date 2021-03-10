package com.learning.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

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
        private AtomicInteger cnt = new AtomicInteger();

        public int add() {
            return cnt.incrementAndGet();
        }

        public int get() {
            return cnt.get();
        }
    }
}
