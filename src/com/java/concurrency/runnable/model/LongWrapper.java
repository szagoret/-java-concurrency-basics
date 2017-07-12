package com.java.concurrency.runnable.model;

/**
 * Created by szagoret on 11.07.2017.
 */
public class LongWrapper {

    private Object object = new Object();

    private long l;

    public LongWrapper(long l) {
        this.l = l;
    }

    public long getValue() {
        return l;
    }

    public void setValue(long l) {
        this.l = l;
    }

    public void incrementValue() {
        synchronized (object) {
            l = l + 1;
        }
    }
}
