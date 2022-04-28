package io.dama.ffi.threads.runnable.loesung;

class MyWorkerCoop implements Runnable {
	private Thread self;

	@Override
	public void run() {
		this.self = Thread.currentThread();
		while (true) {
			System.out.println(this.self.getName() + ": ID => " + this.self.getId());
			Thread.yield(); // Thread wird nach jeder Ausgabe freigegeben und wieder in die Warteschlange
							// eingereiht
		}
	}
}
