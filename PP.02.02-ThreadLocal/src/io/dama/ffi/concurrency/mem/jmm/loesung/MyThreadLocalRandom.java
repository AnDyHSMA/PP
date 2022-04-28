package io.dama.ffi.concurrency.mem.jmm.loesung;

import java.util.Random;

// Ein Random-Objekt wird beim Instanziieren von runner im Thread-lokalen Speicher
// vorbereitet. Sobald später aus mehreren Threads darauf zugegriffen wird, wird jeweils
// Thread-lokal eine neue Instanz von Random erzeugt, die alle denselben Seed verwenden,
// deshalb wird in den 10 Threads jeweils dieselbe Sequenz von 20 Zufallszahlen gezogen.
public class MyThreadLocalRandom implements Runnable {
	public static long now = System.currentTimeMillis();
	public ThreadLocal<Random> rand = new ThreadLocal<>() {
		@Override
		protected Random initialValue() {
			return new Random(now);
		}
	};

	@Override
	public void run() {
		var strBuf = new StringBuffer();
		strBuf.append(Thread.currentThread().getName() + ": ");
		for (var j = 0; j < 20; j++) {
			strBuf.append(String.format("%2d ", this.rand.get().nextInt(100)));
		}
		System.out.println(strBuf);
	}

	public static void main(String... args) {
		var runner = new MyThreadLocalRandom();
		for (var i = 0; i < 10; i++) {
			new Thread(runner, String.format("Runner-%02d", i)).start();
		}
	}
}
