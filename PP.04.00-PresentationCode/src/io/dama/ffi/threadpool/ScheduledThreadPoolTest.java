package io.dama.ffi.threadpool;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ScheduledThreadPoolTest {

    public static void main(String... args) throws ParseException {
        ScheduledExecutorService scheduler = Executors
                .newScheduledThreadPool(1);
        ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(
                () -> System.out.println("beep"), 3, 3, SECONDS);
        scheduler.schedule(() -> beeperHandle.cancel(true), 5 * 3, SECONDS);
        scheduler.schedule(() -> System.exit(0), (5 * 3) + 5, SECONDS);
        System.out.println("all tasks scheduled");
        Date date = DateFormat.getDateTimeInstance()
                .parse("11.04.2021 20:15:00");
        scheduler.schedule(() -> System.out.println("Tatort fängt an"),
                date.getTime() - System.currentTimeMillis(), MILLISECONDS);
    }
}
