package com.learning.algorithm.other;

public class DisorderMeasurer {
    public static void main(String[] args) {
        DisorderMeasurer dm = new DisorderMeasurer();

        System.out.println(dm.measureDisorder(new int[]{0, 3, 1, 2}));
        System.out.println(dm.measureDisorder(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
        System.out.println(dm.measureDisorder(new int[]{0, 1, 2, 3, 5, 4, 6, 7, 8, 9}));
        System.out.println(dm.measureDisorder(new int[]{8, 9, 4, 6, 3, 7, 0, 5, 2, 1}));
        System.out.println(dm.measureDisorder(new int[]{5, 6, 7, 8, 9, 0, 1, 2, 3, 4}));
        System.out.println(dm.measureDisorder(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
    }

    /**
     * Give a array contains number from 0 to n - 1,
     * each number occurs only one time,
     * return the disorder degree of this array
     * @param array the array
     * @return degree of disorder
     */
    public int measureDisorder(int[] array) {
        int n = array.length, res = 0;
        boolean[] checked = new boolean[n], invalid = new boolean[n];

        for (int cur : array) {
            for (int i = cur - 1; i >= 0; i--) {
                if (!checked[i]) invalid[i] = true;
            }

            checked[cur] = true;
        }

        for (boolean b : invalid) res += b ? 1 : 0;

        return res;
    }
}
