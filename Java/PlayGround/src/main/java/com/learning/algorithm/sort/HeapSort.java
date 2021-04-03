package com.learning.algorithm.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] nums = Nums.getNums(50, 100);

        heapSort(nums, nums.length);
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void heapSort(int[] nums, int topK) {
        int n = nums.length;

        // Get max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjust(nums, i, n);
        }

        for (int i = 0; i < topK; i++) {
            // Adjust heap
            adjust(nums, 0,n - i);
            // Swap nums
            swap(nums, 0, n - i - 1);
        }
    }

    private static void adjust(int[] nums, int i, int len) {
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && nums[k + 1] > nums[k]) k++;
            if (nums[k] > nums[i]) {
                swap(nums, k, i);
                i = k;
            } else break;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}