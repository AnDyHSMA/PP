package threadAbfrage;

public class ThreadAbfrage {

	public static void main(String... args) {
		// Anzahl der Prozessoren abfragen
		var nr = Runtime.getRuntime().availableProcessors();
		System.out.println("Anzahl der Prozessoren: " + nr);

		// Eigenschaften des ”main Threads”
		var self = Thread.currentThread();

		System.out.println("ID : " + self.getId());
		System.out.println("Name : " + self.getName());
		System.out.println("Prio : " + self.getPriority());		
		
		// Priorität ist default 5, kann aber manuell geändert werden
		Thread.currentThread().setPriority(1);
		System.out.println("Prio gesetzt auf: " + self.getPriority());
	}
	
}