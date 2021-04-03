package com.learning.algorithm.sort;

import java.util.Arrays;

/**
 * Merge Sort
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = Nums.getNums(113, 1000);
        System.out.println(Arrays.toString(nums));
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums); // For Check
        System.out.println(Arrays.toString(nums));
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, r);
        merge(nums, l, r);
    }

    private static void merge(int[] nums, int l, int r) {
        int m = l + (r - l) / 2;
        int[] tmp = Arrays.copyOfRange(nums, l, r + 1);
        int li = 0, ri = m - l + 1;
        for (int i = 0; i < r - l + 1; i++) {
            if (li > m - l) nums[l + i] = tmp[ri++];
            else if (ri > r - l) nums[l + i] = tmp[li++];
            else if (tmp[li] > tmp[ri]) nums[l + i] = tmp[ri++];
            else nums[l + i] = tmp[li++];
        }
    }
}
