package com.java.concurrency.runnable;

import com.java.concurrency.runnable.model.LongWrapper;

/**
 * Created by szagoret on 11.07.2017.
 */
public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {
        LongWrapper longWrapper = new LongWrapper(0L);

        Runnable r = () -> {
            for (int i = 0; i < 1_000; i++) {
                longWrapper.incrementValue();
            }
        };

        Thread[] threads = new Thread[1_000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
            threads[i].start();
        }


        for (int i = 0; i < threads.length; i++) {
            // a trick to assure that the code after this instruction
            // will be run only after the code in my thread has been finished to run
            threads[i].join();
        }

        System.out.println("Value = " + longWrapper.getValue());
    }
}
