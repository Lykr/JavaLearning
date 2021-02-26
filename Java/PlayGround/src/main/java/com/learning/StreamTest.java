package com.learning;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        int n = 10, range = 100;
        int[] arr1 = new int[n], arr2 = new int[n];

        for (int i = 0; i < n; i++) {
            arr1[i] = (int)(Math.random() * 100);
            arr2[i] = (int)(Math.random() * 100);
        }

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

        //注意，流不能被重复使用
        int max = Arrays.stream(arr1).max().getAsInt(); //最大值
        int min = Arrays.stream(arr1).min().getAsInt(); //最小值
        double average = Arrays.stream(arr1).average().getAsDouble(); //平均值
        System.out.println("最大值：" + max + "，最小值：" + min + "，平均值：" + average);

    }
}
