package com.learning.algorithm.other;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ReceiveConsumeDelete {
    private final int receiveThreadPoolSize;
    private final int consumeThreadPoolSize;
    private final int deleteThreadPoolSize;

    private final ExecutorService receiveThreadPool;
    private final ExecutorService consumeThreadPool;
    private final ExecutorService deleteThreadPool;

    private final BlockingQueue<List<Message>> waitToConsumeQueue;
    private final BlockingQueue<List<Message>> waitToDeleteQueue;

    private final AtomicLong receiveNum = new AtomicLong();
    private final AtomicLong consumeNum = new AtomicLong();
    private final AtomicLong deleteNum = new AtomicLong();

    /* Action duration in milliseconds */
    private final int receiveDuration;
    private final int consumeDuration;
    private final int deleteDuration;

    public ReceiveConsumeDelete(int receiveThreadPoolSize, int consumeThreadPoolSize, int deleteThreadPoolSize,
                                int waitToConsumeQueueSize, int waitToDeleteQueueSize,
                                int receiveDuration, int consumeDuration, int deleteDuration) {
        this.receiveThreadPoolSize = receiveThreadPoolSize;
        this.consumeThreadPoolSize = consumeThreadPoolSize;
        this.deleteThreadPoolSize = deleteThreadPoolSize;

        this.receiveThreadPool = Executors.newFixedThreadPool(receiveThreadPoolSize);
        this.consumeThreadPool = Executors.newFixedThreadPool(consumeThreadPoolSize);
        this.deleteThreadPool = Executors.newFixedThreadPool(deleteThreadPoolSize);

        this.waitToConsumeQueue = new ArrayBlockingQueue<>(waitToConsumeQueueSize, true);
        this.waitToDeleteQueue = new ArrayBlockingQueue<>(waitToDeleteQueueSize, true);

        this.receiveDuration = receiveDuration;
        this.consumeDuration = consumeDuration;
        this.deleteDuration = deleteDuration;
    }

    public void start() {
        /* Receive */
        for (int i = 0; i < receiveThreadPoolSize; i++) {
            receiveThreadPool.execute(() -> {
                while (true) {
                    try {
                        List<Message> messages = receive();
                        if (messages.size() > 0) {
                            this.waitToConsumeQueue.put(messages);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /* Consume */
        for (int i = 0; i < consumeThreadPoolSize; i++) {
            consumeThreadPool.execute(() -> {
                while (true) {
                    try {
                        List<Message> messages = waitToConsumeQueue.take();
                        consume(messages);
                        waitToDeleteQueue.put(messages);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /* Delete */
        for (int i = 0; i < deleteThreadPoolSize; i++) {
            deleteThreadPool.execute(() -> {
                while (true) {
                    try {
                        List<Message> messages = waitToDeleteQueue.take();
                        delete(messages);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private List<Message> receive() {
        try {
            Thread.sleep(this.receiveDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Message> messages = new ArrayList<Message>() {{
            int size = (int) (Math.random() * 10);
            for (int i = 0; i < size; i++) {
                add(new Message());
            }
        }};
        this.receiveNum.addAndGet(messages.size());
        System.out.println("Receive:" + messages);
        return messages;
    }

    private void consume(List<Message> messages) {
        try {
            Thread.sleep(this.consumeDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.consumeNum.addAndGet(messages.size());
        System.out.println("Consume:" + messages);
    }

    private void delete(List<Message> messages) {
        try {
            Thread.sleep(this.deleteDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.deleteNum.addAndGet(messages.size());
        System.out.println("Delete:" + messages);
    }

    public AtomicLong getReceiveNum() {
        return receiveNum;
    }

    public AtomicLong getConsumeNum() {
        return consumeNum;
    }

    public AtomicLong getDeleteNum() {
        return deleteNum;
    }

    private static class Message {
        private final String id;

        private Message() {
            this.id = UUID.randomUUID().toString().substring(0, 8);
        }

        @Override
        public String toString() {
            return "Message{" +
                    "id=" + id +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int runtime = 1 * 1000;
        ReceiveConsumeDelete rcd = new ReceiveConsumeDelete(4, 4, 4,
                10, 10,
                15, 50, 30);
        rcd.start();
        Thread.sleep(runtime);
        System.out.println("ReceiveNum:" + rcd.getReceiveNum().longValue());
        System.out.println("ConsumeNum:" + rcd.getConsumeNum().longValue());
        System.out.println("DeleteNum:" + rcd.getDeleteNum().longValue());
        System.exit(0);
    }
}
