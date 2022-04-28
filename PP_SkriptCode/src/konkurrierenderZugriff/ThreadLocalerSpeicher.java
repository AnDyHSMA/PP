package konkurrierenderZugriff;

// TLS mit Heap für Objekte, die nur ein bestimmter Thread „sehen“ kann.
// nur für Objekte vom Typ T, wenn über ThreadLocal<T> erzeugt
// → hier ist T Integer
// sehr viel schneller als Heap, braucht nicht synchronisiert werden
public class ThreadLocalerSpeicher {
	
	public static class Runner implements Runnable {
		
		// Es wird ein Objekt einer anonymen Subklasse von ThreadLocal erzeugt, bei
		// der die Methode initialValue() überschrieben ist. Diese Methode wird am Anfang des
		// Lebenszyklus von ThreadLocal-Objekten aufgerufen, um das Thread-lokal gespeicherte
		// Objekt zu initialisieren.
		public static ThreadLocal<Integer> mem = new ThreadLocal<>() {
			@Override
			protected Integer initialValue() {
				return Integer.valueOf(1);
			}
		};

		@Override
		public void run() {
			while (true) {
				// Der Zugriff auf so gewrappte Objekte erfolgt über die Methoden get und set 
				mem.set(mem.get() + 1);
			}
		}
	}

	public static void main(String... args) {
		var runnable = new Runner();
		new Thread(runnable, "Runner-1").start();
		new Thread(runnable, "Runner-2").start();
	}

}
