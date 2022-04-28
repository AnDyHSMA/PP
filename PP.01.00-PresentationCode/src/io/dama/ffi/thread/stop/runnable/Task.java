package io.dama.ffi.thread.stop.runnable;

public class Task implements Runnable {
    private volatile boolean stopped; // (3)
    private volatile Thread self;     // (3)
    public boolean isStopped() {
        return this.stopped;
    }
    public void stopRequest() {
        this.stopped = true;          // (9)
        if (this.self != null) {
            this.self.interrupt();    // (10)
        }
    }
    @Override
    public void run() {               // (6)
        this.self = Thread.currentThread();
        while (!isStopped()) {
            // ... arbeiten ...
        }
        // ... aufräumen ...
    }
}
