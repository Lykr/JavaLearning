package com.learning.juc;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 提供两种实现
 * - FIFO 队列 ：LinkedBlockingQueue、ArrayBlockingQueue（固定长度）
 * - 优先级队列 ：PriorityBlockingQueue
 * 提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，直到队列有空闲位置。
 */
public class BlockingQueueTest {
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();
        consumer.start();
        producer.start();
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    queue.take();
                    System.out.println("Consumer take one, remaining: " + queue.size());
                    Thread.sleep((int) (Math.random() * 100) + 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    queue.put("product");
                    System.out.println("Producer make one, remaining: " + queue.size());
                    Thread.sleep((int) (Math.random() * 100) + 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
