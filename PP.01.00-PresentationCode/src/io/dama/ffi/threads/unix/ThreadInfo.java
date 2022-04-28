package io.dama.ffi.threads.unix;

import java.io.IOException;

public class ThreadInfo {
    final static String NAME = "Rumpelstilzchen";

    public static void main(String... args) throws IOException {
        var t = new Thread(() -> {
            while (true) {
                // "leere" Endlosschleife
            }
        }, NAME);
        t.setDaemon(true);
        t.start();
        var pid = ProcessHandle.current().pid();
        var exec = new ProcessBuilder("/bin/sh", "-c",
                "ps -cT -p " + pid + " | egrep '(PID | " + NAME + ")'");
        exec.inheritIO();
        exec.start();
    }
}
