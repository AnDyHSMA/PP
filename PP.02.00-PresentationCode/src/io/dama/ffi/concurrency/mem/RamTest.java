package io.dama.ffi.concurrency.mem;

public class RamTest extends Thread {
    private final int i;

    public RamTest(int i) {
        this.i = i;
    }

    public void print(int i) {
        var a = i * i;
        var b = Integer.valueOf(a);
        System.out.println(b);
    }

    @Override
    public void run() {
        print(this.i);
    }

    public static void main(String... args) {
        new Thread(new RamTest(2)).start();
        new Thread(new RamTest(3)).start();
    }
}
