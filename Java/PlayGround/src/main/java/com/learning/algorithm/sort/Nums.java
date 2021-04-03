package com.learning.algorithm.sort;

import java.util.Random;

public class Nums {
    /**
     * Get nums array
     * @param n length
     * @param bound bound of element
     * @return
     */
    public static int[] getNums(int n, int bound) {
        int[] nums = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(bound);
        }
        return nums;
    }
}
