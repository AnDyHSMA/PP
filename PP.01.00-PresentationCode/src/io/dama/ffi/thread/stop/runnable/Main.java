package io.dama.ffi.thread.stop.runnable;

public class Main {
    public static void main(String... args) // (1)
            throws InterruptedException {
        var task = new Task();              // (2)
        var thread = new Thread(task);      // (4)
        thread.start();                     // (5)
        Thread.sleep(4000);                 // (7)
        task.stopRequest();                 // (8)
        thread.join();
    }
}