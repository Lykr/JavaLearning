package com.learning.basic;

import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //while (scanner.hasNextLine()) {
        //    System.out.println(scanner.nextLine());
        //}

        System.out.println(scanner.nextInt());
        System.out.println(scanner.nextLong());
    }
}
