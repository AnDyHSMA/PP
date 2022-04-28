package io.dama.ffi.threadpool;

import java.util.concurrent.Executors;

class ThreadPoolTestCached {

    public static void main(String... args) {
        var pool = Executors.newCachedThreadPool();

        for (var i = 0; i <= 100; i++) {
            pool.submit(
                    () -> System.out.println(Thread.currentThread().getName()));
        }

    }
}
