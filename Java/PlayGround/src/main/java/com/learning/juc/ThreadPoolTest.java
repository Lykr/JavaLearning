package com.learning.juc;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        // 工作队列，一般是一个阻塞队列，用于存放等待被执行的任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(64);
        // 当前同时运行的线程数量达到最大线程数量，并且队列也已经被放满时，触发拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
        ExecutorService threadPool = new ThreadPoolExecutor(6,
                12,
                10,
                TimeUnit.SECONDS,
                workQueue,
                rejectedExecutionHandler);
        for (int i = 0; i < 128; i++) {
            try {
                threadPool.execute(new MyRunnable(i));
            } catch (RejectedExecutionException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                i--;
            }
        }
        threadPool.shutdown();
    }

    static class MyRunnable implements Runnable {
        int idx = 0;

        public MyRunnable(int i) {
            this.idx = i;
        }

        @Override
        public void run() {
            System.out.println("Execute Task No." + idx);
            try {
                System.out.println("Task No." + idx + " running in " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task No." + idx + " is done");
        }
    }
}
