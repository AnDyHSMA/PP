package io.dama.ffi.threads.runnable;

public class Starter {
    static int WORKERS = 200;

    public static void main(String... args) {
        for (var i = 0; i < Starter.WORKERS; i++) {
            var t = new Thread(new MyWorker(), String.format("Worker-%03d", i));
            t.start();
        }
    }
}
