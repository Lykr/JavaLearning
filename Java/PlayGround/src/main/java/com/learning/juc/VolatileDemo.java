package com.learning.juc;

public class VolatileDemo {
    // Delete 'volatile' will result in looping forever.
    private static volatile boolean isOver = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) {
                    // Do not use System.out.println(), because it has a synchronized in it, which will results in
                    // copying main memory to working memory.
                    int a = 1;
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
    }
}
