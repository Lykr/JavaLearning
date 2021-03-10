package com.learning.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int ans = 0;

            for (int i =0; i < 100; i++) {
                ans += i;
                Thread.sleep(20);
            }

            System.out.println("Future task over");

            return ans;
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        Thread otherThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Other thread over");
        });
        otherThread.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
