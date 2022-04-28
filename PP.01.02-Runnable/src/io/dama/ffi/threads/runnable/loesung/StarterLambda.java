package io.dama.ffi.threads.runnable.loesung;

public class StarterLambda {
	static int WORKERS = 200;

	public static void main(String... args) {
		for (var i = 0; i < Starter.WORKERS; i++) {
			var t = new Thread(() -> {
				var self = Thread.currentThread();
				while (true) {
					System.out.println(self.getName() + ": ID => " + self.getId());
				}
			}, String.format("Worker-%03d", i));
			t.start();
		}
	}
}
