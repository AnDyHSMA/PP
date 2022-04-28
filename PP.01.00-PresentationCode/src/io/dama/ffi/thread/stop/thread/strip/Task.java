package io.dama.ffi.thread.stop.thread.strip;

public class Task extends Thread {
    private volatile boolean stopped;


    public boolean isStopped() {
        return this.stopped;
    }

    public void stopRequest() {
        this.stopped = true;
        if (this.isAlive()) {
            this.interrupt();
        }
    }

    @Override
    public void run() {

        while (!isStopped()) {
            // ... arbeiten ...
        }
        // ... aufräumen ...
    }
}