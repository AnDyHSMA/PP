package konkurrierenderZugriff;

// Nun ist es aber komplizierter: Die beiden Instruktionen counter = counter + 3
// bzw. counter = counter + 4 bestehen tatsächlich aus mehreren Schritten, die in der
// Implementierung von add angedeutet ist: - Zuerst muss der aktuelle Wert der Instanzvariablen
// counter in eine (lokale) Puffervarible temp kopiert werden. - Dort wird die der Parameter i
// hinzuaddiert. - Das Ergebnis, das in temp gespeichert ist, wird zurück in die Instanzvariable
// gespeichert.
// Wenn Sie nun einwenden, dass dies auch ohne den Umweg über die lokale Puffervariable temp
// realisiert werden kann (this.counter += i), haben Sie zwar Recht, aber man darf nicht
// davon ausgehen, dass diese Instruktion atomar wäre. Der Java-Compiler macht daraus im JavaByte-Code im Class-File auch den Umweg über eine Puffervariable.
// Es kann im gezeigten Beispiel u.a. zu folgenden Traces kommen, die vor allem zu einem
// unterschiedlichen Wert der Variablen c.counter führen:


public class Counter2 {

	int counter = 1;

	void add(int i) {
		var temp=this.counter;
		temp=temp+i;
		this.counter = temp;
	}

	public static void main(String[] args) throws InterruptedException {
		var c=new Counter2();
		
		// c ist ein Objekt im Heap.
		// Die beiden Threads t1 und t2 haben jeweils eine Referenz auf dieses Objekt im Heap(„Closure“).
		// Beide Threads greifen konkurrierend auf die Instanzvariable counter von c zu.
		// Die Ausgabe kann entweder 2 oder 3 sein.
		var t3=new Thread(()->c.add(3));
		var t4=new Thread(()->c.add(4));
		t3.start();
		t4.start();
		t3.join();
		t4.join();
		System.out.println(c.counter);
	}
}

