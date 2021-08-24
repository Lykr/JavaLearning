package com.learning.algorithm.other;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadsBatcher<E> {
    private final int batchSize;
    private final int timeout; // in milliseconds
    private final ConcurrentLinkedQueue<E> queue;

    public MultiThreadsBatcher(int batchSize, int timeout) {
        this.batchSize = batchSize;
        this.timeout = timeout;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public static void main(String[] args) throws InterruptedException {
        int transNum = 1000;
        MultiThreadsBatcher<Integer> mtb = new MultiThreadsBatcher<>(10, 1);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        CountDownLatch latch = new CountDownLatch(transNum);

        scheduledExecutorService.scheduleAtFixedRate(() -> mtb.consume(latch), 0, 20, TimeUnit.MILLISECONDS);

        for (int i = 0; i < transNum; i++) {
            final int num = i;
            threadPool.execute(() -> mtb.store(num));
        }

        latch.await();

        /* Must shutdown thread pool to exit, because they are not daemon threads */
        threadPool.shutdown();
        scheduledExecutorService.shutdown();
    }

    public void store(E obj) {
        this.queue.offer(obj);
    }

    public void consume(CountDownLatch latch) {
        List<E> list = new LinkedList<>();

        for (int i = 0; i < this.batchSize; i++) {
            E obj = this.queue.poll();
            if (obj != null) {
                list.add(obj);
                latch.countDown();
            }
        }

        System.out.println(list + "" + latch.getCount());
    }
}
