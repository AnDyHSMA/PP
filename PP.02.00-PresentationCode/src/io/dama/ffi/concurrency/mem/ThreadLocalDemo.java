package io.dama.ffi.concurrency.mem;

import java.util.Random;

public class ThreadLocalDemo {
    public static class Runner implements Runnable {
        public static ThreadLocal<Integer> mem = 
            new ThreadLocal<>() {
                @Override
                protected Integer initialValue() {
                    return Integer.valueOf(1);
                }
            };
        @Override
        public void run() {
            while (true) {
                mem.set(mem.get() + 1);
            }
        }
    }
    public static void main(String... args) {
        var runnable = new Runner();
        new Thread(runnable, "Runner-1").start();
        new Thread(runnable, "Runner-2").start();
    }
}
