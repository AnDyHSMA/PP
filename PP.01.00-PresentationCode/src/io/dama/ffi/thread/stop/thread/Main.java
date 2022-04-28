package io.dama.ffi.thread.stop.thread;

// import io.dama.ffi.thread.stop.thread.strip.Task;

public class Main {
    public static void main(String... args) //
            throws InterruptedException {
        var thread = new Task();
        thread.start();
        Thread.sleep(4000);
        thread.stopRequest();
        thread.join();
    }
}