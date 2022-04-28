package io.dama.ffi.parcoord.synch.loesung;

import java.util.Random;

public class Philosopher extends Thread {
    static final int MAX_THINKING_DURATION_MS = 1000;
    static final int MAX_EATING_DURATION_MS = 3000;
    static final int MAX_TAKING_TIME_MS = 1000;

    private final Chopstick left;
    private final Chopstick right;
    private final Random random;
    private int eaten;
    private final int seat;

    private volatile boolean stop;

    private void log(String message) {
        synchronized (Philosopher.class) {
            for (var i = 1; i <= this.seat; i++) {
                System.out.print("                         ");
            }
            System.out.println(getId() + " " + message);
        }
    }

    public void stopPhilosopher() {
        log("stopping");
        this.stop = true;
        interrupt();
    }

    public Philosopher(int seat, Chopstick left, Chopstick right) {
        this.stop = false;
        this.seat = seat;
        this.left = left;
        this.right = right;
        this.random = new Random();
        this.eaten = 0;
    }

    @Override
    public void run() {
        log("starting");
        try {
            while (!this.stop) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log("stopped; eaten=" + this.eaten);
    }

    private void think() throws InterruptedException {
        Thread.sleep(this.random.nextInt(MAX_THINKING_DURATION_MS));
    }

    private void eat() throws InterruptedException {
        log("try taking left");
        synchronized (this.left) {
            log("left acquired");
            Thread.sleep(this.random.nextInt(MAX_TAKING_TIME_MS));
            log("try taking right");
            synchronized (this.right) {
                log("right acquired");
                log("eating");
                this.eaten++;
                Thread.sleep(this.random.nextInt(MAX_EATING_DURATION_MS));
            }
            log("right released");
        }
        log("left released");
    }
}
