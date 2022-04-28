package threadsErstellen;

public class ThreadErstellenDurchImplementRunnable implements Runnable {

	// wird durch run() aufgerufen
	@Override
	public void run() {
		System.out.println("Ich bin Thread durch Runnable");		
	}

}
