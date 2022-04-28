package io.dama.ffi.threads.all;

class PrioSched {
    final static boolean YIELD = true;
    final static int SLEEP = 1000;

    static volatile boolean stop;
    static int norm;
    static int mini;
    static int maxi;
    static int defa;

    public static void main(String... args) throws InterruptedException {
        System.out.println("PID: " + ProcessHandle.current().pid());

        var t0 = new Thread(() -> {
            while (!stop) {
                norm++;
                if (YIELD) {
                    Thread.yield();
                }
            }
        }, "Thread-Norm");
        t0.setPriority(Thread.NORM_PRIORITY);

        var t1 = new Thread(() -> {
            while (!stop) {
                maxi++;
                if (YIELD) {
                    Thread.yield();
                }
            }
        }, "Thread-Maxi");
        t1.setPriority(Thread.MAX_PRIORITY);

        var t2 = new Thread(() -> {
            while (!stop) {
                mini++;
                if (YIELD) {
                    Thread.yield();
                }
            }
        }, "Thread-Mini");
        t2.setPriority(Thread.MIN_PRIORITY);

        var t3 = new Thread(() -> {
            while (!stop) {
                defa++;
                if (YIELD) {
                    Thread.yield();
                }
            }
        }, "Thread-Default");

        t0.start();
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(SLEEP);
        stop = true;

        t0.join();
        t1.join();
        t2.join();
        t3.join();

        System.out.println("Yield: " + (YIELD ? "ja" : "nein"));
        System.out.printf("Maximal Prio: %2.1f%%\n",
                100.0 * maxi / (maxi + norm + defa + mini));
        System.out.printf("Normale Prio: %2.1f%%\n",
                100.0 * norm / (maxi + norm + defa + mini));
        System.out.printf("Default Prio: %2.1f%%\n",
                100.0 * defa / (maxi + norm + defa + mini));
        System.out.printf("Minimum Prio: %2.1f%%\n",
                100.0 * mini / (maxi + norm + defa + mini));
    }
}