package com.learning.algorithm.sort;

import java.util.Arrays;

/**
 * Bubble sort
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = Nums.getNums(113, 1000);
        System.out.println(Arrays.toString(nums));
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums); // For Check
        System.out.println(Arrays.toString(nums));
    }

    private static void bubbleSort(int[] nums) {
        boolean over = false;
        while (!over) {
            over = true;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i - 1] > nums[i]) {
                    swap(nums, i - 1, i);
                    over = false;
                }
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
