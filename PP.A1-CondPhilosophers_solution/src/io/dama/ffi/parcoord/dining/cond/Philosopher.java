package io.dama.ffi.parcoord.dining.cond;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread implements IPhilosopher {
    private boolean eating;
    private Philosopher left;
    private Philosopher right;
    private Lock table;
    private Condition condition;
    private final Random random;
    private int eaten;
    private int seat;

    private volatile boolean stop;

    private void log(String message) {
        synchronized (Philosopher.class) {
            for (var i = 1; i <= this.seat; i++) {
                System.out.print("                         ");
            }
            System.out.println(getId() + " " + message);
        }
    }

    @Override
    public void stopPhilosopher() {
        log("stopping");
        this.stop = true;
        interrupt();
    }

    public Philosopher() {
        this.eaten = 0;
        this.eating = false;
        this.random = new Random();
        this.stop = false;
    }

    @Override
    public void setLeft(IPhilosopher left) {
        this.left = (Philosopher) left;
    }

    @Override
    public void setRight(IPhilosopher right) {
        this.right = (Philosopher) right;
    }

    @Override
    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public void setTable(Lock table) {
        this.table = table;
        this.condition = this.table.newCondition();
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
        this.table.lock();
        try {
            if (this.eating) { // Fallunterscheidung für den Beginn des
                               // Experiments
                log("left and right released");
                this.eating = false;
            }
            this.left.condition.signal();
            this.right.condition.signal();
        } finally {
            this.table.unlock();
        }
        Thread.sleep(this.random
                .nextInt(PhilosopherExperiment.MAX_THINKING_DURATION_MS));
    }

    private void eat() throws InterruptedException {
        this.table.lock();
        try {
            while (this.left.eating || this.right.eating) {
                log("try taking left or right");
                this.condition.await();
            }
            log("left and right acquired");
            log("eating");
            this.eating = true;
            this.eaten++;
        } finally {
            this.table.unlock();
        }
        Thread.sleep(this.random
                .nextInt(PhilosopherExperiment.MAX_EATING_DURATION_MS));
        log("finished eating");
    }
}
