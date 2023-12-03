package com.hqhe.juc;


import java.util.concurrent.LinkedTransferQueue;

public class DeadLock {
    private volatile Integer a = 1;
    public static String stringA = "qq";
    public static String stringB = "hh";
    private LinkedTransferQueue queue;

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    public void deadLock() {
        Thread thread_A = new Thread(() -> {
            synchronized (stringA) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (stringB) {
                    System.out.println(stringB);
                }
            }
        }, "线程A");

        Thread thread_B = new Thread(() -> {
            synchronized (stringB) {
                synchronized (stringA) {
                    System.out.println(stringA);
                }
            }
        }, "线程B");
        thread_A.start();
        thread_B.start();
    }
}
