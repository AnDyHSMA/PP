package threadsErstellen;

public class ThreadErstellenDurchExtendThread extends Thread {

	// wird durch start() aufgerufen
	@Override
	public void run() {
		System.out.println("Ich bin Thread durch Thread");
	}

}
