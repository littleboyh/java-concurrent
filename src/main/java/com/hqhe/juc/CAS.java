package com.hqhe.juc;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CAS {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final CAS cas = new CAS();
        List<Thread> threads = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10_000; j++) {
                    cas.unsafeCount();
                    cas.safeCount();
                }
            });
            threads.add(thread);
        }

        threads.stream().forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        for (; ; ) {
            int expected = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(expected, ++expected);
            if (suc) break;
        }
    }

    private void unsafeCount() {
        i++;
    }
}
