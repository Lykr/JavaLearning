package com.learning.juc;

import java.util.concurrent.*;

public class CallableTest {
    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            int res = 0;
            for (int i = 0; i < 100; i++) {
                res += i;
            }
            Thread.sleep(500);
            return res;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        Thread thread = new Thread(futureTask);

        thread.start();

        int retries = 3;
        Integer res = null;

        while (retries-- > 0) {
            try {
                res = futureTask.get(1000, TimeUnit.MILLISECONDS);
                break;
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }

        System.out.println(res);
    }
}
