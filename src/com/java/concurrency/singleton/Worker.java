package com.java.concurrency.singleton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by szagoret on 14.07.2017.
 */
public class Worker extends Thread {

    private Object lock = new Object();
    private volatile boolean quittingTime = false;

    @Override
    public void run() {
        while (!quittingTime) {
            working();
            System.out.println("Still working...");
        }
        System.out.println("Coffee is good !");
    }

    private void working() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void quit() throws InterruptedException {
        synchronized (lock) {
            quittingTime = true;
            System.out.println("Calling join");
            join();
            System.out.println("Back from join");
        }
    }

    private synchronized void keepWorking() {
        synchronized (lock) {
            quittingTime = false;
            System.out.println("Keep working");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Worker worker = new Worker();
        worker.start();

        Timer timer = new Timer(true); // Daemon thread
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                worker.keepWorking();
            }
        }, 500);

        Thread.sleep(400);
        worker.quit();
    }

}
