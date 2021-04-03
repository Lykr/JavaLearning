package com.learning.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Select a number, which larger than all numbers on the left and smaller than all numbers on the right
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = Nums.getNums(100, 1000);
        System.out.println(Arrays.toString(nums));
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums); // For Check
        System.out.println(Arrays.toString(nums));
    }

    /**
     * QuickSort function
     * @param nums
     * @param l index of left point indicating left bound of current data
     * @param r index of right point indicating right bound of current data
     */
    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int i = l, j = r, taker = nums[l];
        while (i < j) {
            while (i < j && nums[j] > taker) j--;
            if (i < j) nums[i++] = nums[j];
            while (i < j && nums[i] < taker) i++;
            if (i < j) nums[j--] = nums[i];
        }
        nums[i] = taker;
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }
}
