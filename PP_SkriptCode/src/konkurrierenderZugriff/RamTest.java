package konkurrierenderZugriff;

public class RamTest extends Thread {

	private final int i;

	public RamTest(int i) {
		this.i = i;
	}

	public void print(int i) {
		var a = i * i;
		var b = Integer.valueOf(a);
		System.out.println(b);
	}

	@Override
	public void run() {
		print(this.i);
	}

	public static void main(String... args) {
		// Am RamTest-Objekt wird im „main Thread“ die start-Methode aufgerufen, so dass die
		// Methode run des RamTest-Objekts beginnt, nebenläufig zu laufen. Deshalb wird ein neuer
		// Stack für den neuen Thread erzeugt. Eine Referenz auf ‘ this, also dasRamTest-Objekt (erbt vonThread“),
		// wird auf dem Stack gespeichert
		new Thread(new RamTest(2)).start();
		new Thread(new RamTest(3)).start();

		// alternativ

//		new RamTest(2).start();
//		new RamTest(3).start();

//		RamTest t1=new RamTest(2); // Der Aufruf des Konstruktors erzeugt ein neues Objekt im Heap und setzt dessen Instanzvariable i auf den Wert 2.
//		t1.start();
//		RamTest t2=new RamTest(3);
//		t2.start();

	}

}
