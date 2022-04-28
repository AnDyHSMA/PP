package io.dama.ffi.concurrency.mem.synch;

import io.dama.ffi.concurrency.mem.synch.model.Type;

public class Factory {

    private static Type instance;

    public static Type getInstance() {
        Type.prepare();
        if (Factory.instance == null) {
            Factory.instance = new Type();
        }
        return Factory.instance;
    }

    public static void main(String... args) throws InterruptedException {
        var now = System.currentTimeMillis();
        var threads = new Thread[100];
        for (var i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                Type object = Factory.getInstance();
                System.out.println(Thread.currentThread().getName()
                        + ": serial of instance = " + object.getSerial());
            }, String.format("InstanceGrabber-%02d", i));
            threads[i].start();
        }
        for (var i = 0; i < 100; i++) {
            threads[i].join();
        }
        var time = System.currentTimeMillis() - now;
        System.out.println("Dauer: " + time + "ms");
    }

}
