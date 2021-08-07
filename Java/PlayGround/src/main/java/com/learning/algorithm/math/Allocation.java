package com.learning.algorithm.math;

import java.util.ArrayList;
import java.util.List;

public class Allocation {
    public static void main(String[] args) {
        // How many schemes for m apples allocating to n peoples?
        // C(m + n - 1, m)
        int m = 16, n = 16;
        for (int i = 0; i < n; i++) res.add(0);
        solution(m, n, 0);
        System.out.println(ans);
    }

    private static List<Integer> res = new ArrayList<>();
    private static int ans = 0;

    private static void solution(int resource, int taker, int curTaker) {
        if (resource == 0) {
            ans++;
            return;
        }

        for (int i = curTaker; i < taker; i++) {
            res.set(i, res.get(i) + 1);
            solution(resource - 1, taker, i);
            res.set(i, res.get(i) - 1);
        }
    }
}
