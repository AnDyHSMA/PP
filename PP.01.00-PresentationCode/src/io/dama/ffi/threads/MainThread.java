package io.dama.ffi.threads;

/* entnommen aus Hettel & Tran 2016, S. 12 */
public class MainThread {
    public static void main(String... args) {
        // Anzahl der Prozessoren abfragen
        var nr = Runtime.getRuntime().availableProcessors();
        System.out.println("Anzahl der Prozessoren " + nr);
        // Eigenschaften des "main Threads"
        var self = Thread.currentThread();
        System.out.println("Name : " + self.getName());
        System.out.println("Prio : " + self.getPriority());
        System.out.println("ID : " + self.getId());
    }
}