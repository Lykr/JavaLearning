package com.learning.algorithm.other;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadsBatcher<E> {
    private final int batchSize;
    private final int timeout; // in milliseconds
    private final ConcurrentLinkedQueue<E> queue;

    private long lastConsumeTime;

    public MultiThreadsBatcher(int batchSize, int timeout) {
        this.batchSize = batchSize;
        this.timeout = timeout;
        this.queue = new ConcurrentLinkedQueue<>();
        this.lastConsumeTime = System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        int transNum = 1000;
        MultiThreadsBatcher<Integer> mtb = new MultiThreadsBatcher<>(10, 1);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        CountDownLatch latch = new CountDownLatch(transNum);

        scheduledExecutorService.scheduleAtFixedRate(mtb::consume, 0, 20, TimeUnit.MILLISECONDS);

        for (int i = 0; i < transNum; i++) {
            final int num = i;
            threadPool.execute(() -> {
                mtb.store(num);
                latch.countDown();
            });
        }

        latch.await();
    }

    public void store(E obj) {
        long curTime = System.currentTimeMillis();

        this.queue.offer(obj);

        if (this.queue.size() >= this.batchSize || curTime - this.lastConsumeTime >= this.timeout) {
            this.lastConsumeTime = curTime;
            consume();
        }
    }

    public void consume() {
        List<E> list = new LinkedList<>();

        for (int i = 0; i < this.batchSize; i++) {
            list.add(this.queue.poll());
        }

        System.out.println(list);
    }
}
