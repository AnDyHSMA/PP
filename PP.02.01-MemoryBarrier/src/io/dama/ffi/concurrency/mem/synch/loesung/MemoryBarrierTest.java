package io.dama.ffi.concurrency.mem.synch.loesung;

public class MemoryBarrierTest extends Thread {

	// eine Möglichkeit Variable als volatile zu definieren
	public volatile boolean stopped = false;

	// andere Möglichkeit synchronized-Block zu implementieren
	@Override
	public void run() {
			while (!this.stopped) {
				// jedes synchronized wirkt als Memory-Barrier
				// work
				synchronized (this) {
					
				}
			}
		

		System.out.println("MemoryBarrierTest-Thread actually stopped.");
	}

	//	Durch das Setzen von stopped soll der MemoryBarrierTest-Thread kontrolliert beendet
	//	werden, indem die while-Schleife und damit die run()-Methode des Threads terminiert.
	//	Wenn stopped aber aus einem anderen Thread heraus geändert wird, kann es sein, dass
	//	die Speicheränderung lokal im Speicher-Cache des ändernden Threads verbleibt und dem
	//	MemoryBarrierTest-Thread nicht übermittelt wird. Auf einem Single-Core-System wird
	//	dies zwar nie passieren, denn bei jedem Kontextwechsel durch den Scheduler wird auch
	//	der Thread-lokale Speicher-Cache synchronisiert, aber auf Multicore-Systemen wird es
	//	wahrscheinlich dazu kommen, dass der Thread nicht korrekt terminiert.
	public static void main(String... args) throws InterruptedException {
		var t = new MemoryBarrierTest();
		t.start();
		Thread.sleep(1000);
		t.stopped = true;
		System.out.println("Main thread set stopped on MemoryBarrierTest-Thread.");
	}

}
