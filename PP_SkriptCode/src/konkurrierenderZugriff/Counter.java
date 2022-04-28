package konkurrierenderZugriff;

public class Counter {

	int counter = 1;

	void set(int i) {
		this.counter = i;
	}

	public static void main(String[] args) throws InterruptedException {
		var c=new Counter();
		
		// c ist ein Objekt im Heap.
		// Die beiden Threads t1 und t2 haben jeweils eine Referenz auf dieses Objekt im Heap(„Closure“).
		// Beide Threads greifen konkurrierend auf die Instanzvariable counter von c zu.
		// Die Ausgabe kann entweder 2 oder 3 sein.
		var t1=new Thread(()->c.set(2));
		var t2=new Thread(()->c.set(3));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(c.counter);
	}

}
