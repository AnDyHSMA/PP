package io.dama.ffi.future;

import java.util.concurrent.Executors;

public class Main {

    public static void main(String... args) {
        var executor = Executors.newCachedThreadPool();
        // hier programmieren
        executor.shutdown();
    }

}
