package io.dama.ffi.pool.inspect;

import io.dama.ffi.pool.run.cached.Runner;

public class Task implements Runnable {

    private static final int NUMBER_OF_TASKS = 1;

    @Override
    public void run() {
        // TODO
    }

    public static void main(String... args) {
        var pool = Runner.test(new Task(), NUMBER_OF_TASKS);
    }

}