package io.dama.ffi.concurrency.mem;

import java.util.Vector;

public class SynchVector1 {
    private static final int MAX = 100;
    private static Vector<Integer> vec = new Vector<>();
    public static void main(String... args) //
            throws InterruptedException {
        for (var i = 0; i < MAX; i++) {
            vec.add(i);
        }
        var remover = new Thread(() -> {
            synchronized (vec) {
                for (var i = 0; i < vec.size(); i++) {
                    if ((vec.get(i) % 2) == 1) {
                        vec.remove(i);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }, "Odd-Remover");
        var adder = new Thread(() -> {
            synchronized (vec) {
                for (var i = 0; i < MAX; i++) {
                    vec.add(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }, "Adder");
        remover.start(); adder.start();
        remover.join(); adder.join();
        for (var element : vec) {
            if ((element % 2) == 1) {
                System.out.print(element + " ");
            }
        }
        System.out.println();
    }
}
