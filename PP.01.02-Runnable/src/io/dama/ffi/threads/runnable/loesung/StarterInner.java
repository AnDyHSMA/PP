package io.dama.ffi.threads.runnable.loesung;

public class StarterInner {
	static int WORKERS = 200;

	public static void main(String... args) {
		for (var i = 0; i < Starter.WORKERS; i++) {
			var t = new Thread(new Runnable() {
				private Thread self;

				@Override
				public void run() {
					this.self = Thread.currentThread();
					while (true) {
						System.out.println(this.self.getName() + ": ID => " + this.self.getId());
					}
				}

			}, String.format("Worker-%03d", i));
			t.start();
		}
	}
}