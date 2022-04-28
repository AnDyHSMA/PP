package io.dama.ffi.threadpool.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class CompletionServiceUsage {

    public static void main(String... args) {
        var pool = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> {
            Thread.sleep(3000);
            return "calc c1";
        });
        tasks.add(() -> {
            Thread.sleep(2000);
            return "calc c2";
        });
        tasks.add(() -> {
            Thread.sleep(1000);
            return "calc c3";
        });
        var completionService = new ExecutorCompletionService<String>(pool);
        for (var callableTask : tasks) {
            completionService.submit(callableTask);
        }
        try {
            for (var i = 0; i < tasks.size(); i++) {
                var future = completionService.take();
                System.out.printf("Result %2d: %s\n", i, future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
        pool.shutdown();
    }
}
