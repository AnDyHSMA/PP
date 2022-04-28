package io.dama.ffi.concurrency.mem;

class Counter {
    int counter = 1;

    void set(int i) {
        this.counter = i;
    }

    void add(int i) {
        var temp = this.counter;
        temp = temp + i;
        this.counter = temp;
    }

    public static void main(String... args) throws InterruptedException {
        var c = new Counter();
        var t1 = new Thread(() -> c.set(2));
        var t2 = new Thread(() -> c.set(3));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(c.counter);

        var t3 = new Thread(() -> c.add(3));
        var t4 = new Thread(() -> c.add(4));
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(c.counter);
    }
}
