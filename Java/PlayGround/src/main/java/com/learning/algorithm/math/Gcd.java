package com.learning.algorithm.math;

/**
 * Greatest Common Divisor (GCD)
 */
public class Gcd {
    public static void main(String[] args) {
        System.out.println(gcd1(98, 42));
        System.out.println(gcd2(98, 42));
    }

    private static int gcd1(int a, int b) {
        while (a != b) {
            if (a > b) a -= b;
            else b -= a;
        }
        return a;
    }

    private static int gcd2(int a, int b) {
        return b == 0 ? a : gcd2(b, a % b);
    }
}
