package io.dama.ffi.parcoord.synch.loesung;

public class PhilosopherExperiment {

	static final int PHILOSOPHER_NUM = 3;
	static final int EXP_DURATION_MS = 10000;

	public static void main(String... args) throws InterruptedException {
		var chopsticks = new Chopstick[PHILOSOPHER_NUM];
		var philosophers = new Philosopher[PHILOSOPHER_NUM];
		for (int i = 0; i < PHILOSOPHER_NUM; i++) {
			chopsticks[i] = new Chopstick();
		}
		// first n-1 philosophers
		for (var i = 0; i < (PHILOSOPHER_NUM - 1); i++) {
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1)]);
		}
		// nth philosophers: initialize with right+left instead of left+right
		philosophers[PHILOSOPHER_NUM - 1] = new Philosopher(PHILOSOPHER_NUM - 1, chopsticks[0],
				chopsticks[PHILOSOPHER_NUM - 1]);
		for (var i = 0; i < PHILOSOPHER_NUM; i++) {
			philosophers[i].start();
		}
		Thread.sleep(EXP_DURATION_MS);
		for (var i = 0; i < PHILOSOPHER_NUM; i++) {
			philosophers[i].stopPhilosopher();
		}
	}
}
