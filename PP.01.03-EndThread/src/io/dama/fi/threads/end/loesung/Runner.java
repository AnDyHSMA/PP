package io.dama.fi.threads.end.loesung;

public class Runner {
    public static void main(String... args) throws InterruptedException {
        var task = new Task();
        Thread self=new Thread(task);
        self.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Unhandled Exception: " + e.getMessage());
            System.err.println(" Thread: " + t.getId() + " - " + t.getName());
            System.err.println(" Thread State: " + t.getState());
            e.printStackTrace(System.err);
        });
        self.start();
        new Thread(()->
        	task.stopRequest()
        ).start();
    }
}
