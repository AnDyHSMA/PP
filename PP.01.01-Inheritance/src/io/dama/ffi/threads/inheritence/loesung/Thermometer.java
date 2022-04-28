package io.dama.ffi.threads.inheritence.loesung;

import java.util.Random;

public class Thermometer extends Sensor {
    private final Random rand;

    public Thermometer(long frequency) {
        super(frequency);
        this.rand = new Random();
    }

    @Override
    protected String reading() {
        return String.format("(%04d freq.): %3d°C", getFrequency(),
                this.rand.nextInt(100));
    }

    public static void main(String... args) {
        var s1 = new Thermometer(1000);
        var s2 = new Thermometer(3000);
        s1.start();
        s2.start();       
    }
}
