package com.learning.algorithm.search;

public class BinarySearch {
    public static void main(String[] args) {
        int len = 100;
        int[] data = new int[len];

        data[0] = 10;

        for (int i = 1; i < len; i++) {
            data[i] = data[i - 1] + 10;
        }

        int offset = len * 10 - 1;

        long seqTotalTime = 0;
        long binaryTotalTime = 0;

        for (int j = 0; j < offset + 1; j++) {
            //顺序
            long startTime = System.nanoTime();

            for (int i = 0; i < len; i++) {
                if (j < data[i]) {
                    long endTime = System.nanoTime();
                    seqTotalTime += endTime - startTime;
                    break;
                }
            }

            //二分
            startTime = System.nanoTime();
            int left = 0, right = len - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (j < data[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            long endTime = System.nanoTime();
            binaryTotalTime += endTime - startTime;
        }

        System.out.println("顺序遍历时间：" + (seqTotalTime) + "ns");

        System.out.println("二分遍历时间：" + (binaryTotalTime) + "ns");
    }
}
