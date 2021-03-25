package com.learning.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * The HashMap will resize when size reach threshold.
 * Thus, OOM happens when there are still some free memory but not enough for resizing.
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<Long, Integer> map = new HashMap<>();
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Max memory: " + runtime.maxMemory());
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            map.put(i, 0);
            if (i % 1000000 == 0) System.out.println("Insert No." + i + "; Free Memory: " + runtime.freeMemory());
        }
    }
}
