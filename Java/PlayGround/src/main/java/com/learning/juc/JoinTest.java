package com.learning.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JoinTest {

    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(8);
        Killer killer = new Killer();
        Cooker cooker = new Cooker(killer);
        Eater eater = new Eater(cooker);

        eater.start();
        cooker.start();
        killer.start();
        //threadPool.shutdown();
    }

    static class Killer extends Thread {
        @Override
        public void run() {
            System.out.println("I kill it.");
        }
    }

    static class Cooker extends Thread {
        private Killer killer;

        public Cooker(Killer killer) {
            this.killer = killer;
        }

        @Override
        public void run() {
            try {
                killer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I cook it.");
        }
    }

    static class Eater extends Thread {
        private Cooker cooker;

        public Eater(Cooker cooker) {
            this.cooker = cooker;
        }

        @Override
        public void run() {
            try {
                cooker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I eat it.");
        }
    }
}
