package io.dama.ffi.threads.inheritence.loesung;

public class Sensor extends Thread {
    // eigentlich abstract

    private final long frequency;

    public Sensor(long frequency) {
        this.frequency = frequency;
//        start(); niemals in einem Konstruktor definieren, gehört in die main-Methode
    }

    /**
     * @return the frequency
     */
    public long getFrequency() {
        return this.frequency;
    }

    protected String reading() {
        // eigentlich abstract
        return null;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("reading: " + reading());
            try {
                Thread.sleep(this.frequency);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String... args) {
        var s = new Sensor(1000);
    }
}
