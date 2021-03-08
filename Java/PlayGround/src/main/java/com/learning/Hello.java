package com.learning;

public class Hello {
    static {
        System.out.println("Hello class static block.");
    }

    {
        System.out.println("Hello class non-static block.");
    }

    public Hello() {
        System.out.println("Hello class constructor.");
    }

    public static void hello() {
        System.out.println("Hello!");
    }
}
