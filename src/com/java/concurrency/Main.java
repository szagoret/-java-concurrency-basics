package com.java.concurrency;

public class Main {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("I'm running in "+Thread.currentThread().getName());
        };

        Thread t = new Thread(runnable);
        t.setName("My thread");

        t.start(); // running in my thread
        //t.run(); // running in main thread

    }
}
