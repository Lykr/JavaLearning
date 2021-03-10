package com.learning.juc;

import java.util.concurrent.*;

/**
 * Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 *
 * 以下代码模拟了对某个服务的并发请求，每次只能有 3 个客户端同时访问，请求总数为 10。
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(3);

        System.out.println("Semaphore at beginning: " + semaphore.availablePermits());

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(finalI + ": " + semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

        System.out.println("end");
        threadPool.shutdown();
    }
}
