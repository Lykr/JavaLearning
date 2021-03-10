package com.learning.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinTask forkJoinTask = new ForkJoinTask(0, 1000000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(forkJoinTask);
        try {
            System.out.println(forkJoinTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class ForkJoinTask extends RecursiveTask<Integer> {
        private final int threshold = 10;
        private final int first, last;

        public ForkJoinTask(int first, int last) {
            if (first > last) first = last;
            this.first = first;
            this.last = last;
        }

        @Override
        protected Integer compute() {
            int result = 0;
            if (last - first < threshold) {
                for (int i = first; i <= last; i++) {
                    result += i;
                }
            } else {
                int mid = first + (last - first) / 2;
                ForkJoinTask leftTask = new ForkJoinTask(first, mid);
                ForkJoinTask rightTask = new ForkJoinTask(mid + 1, last);
                leftTask.fork();
                rightTask.fork();
                result = leftTask.join() + rightTask.join();
            }
            return result;
        }
    }
}
