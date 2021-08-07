package com.learning.basic;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer[]> queue = new PriorityQueue<>((x, y) -> x[1] - y[1]);
        queue.offer(new Integer[]{1, 1});
        queue.offer(new Integer[]{2, 2});
        queue.remove(new Integer[]{1, 1});
        for (Integer[] arr : queue) {
            System.out.printf(Arrays.toString(arr));
        }
    }
}
