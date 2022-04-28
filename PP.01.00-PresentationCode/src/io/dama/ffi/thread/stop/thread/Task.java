package io.dama.ffi.thread.stop.thread;

public class Task extends Thread {
    private volatile boolean stopped;
    private volatile Thread self;

    public boolean isStopped() {
        return this.stopped;
    }

    public void stopRequest() {
        this.stopped = true;
        if (this.self != null) {
            this.self.interrupt();
        }
    }

    @Override
    public void run() {
        this.self = Thread.currentThread();
        while (!isStopped()) {
            // ... arbeiten ...
        }
        // ... aufräumen ...
    }
}