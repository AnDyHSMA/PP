package threadsErstellen;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		ThreadErstellenDurchExtendThread te=new ThreadErstellenDurchExtendThread();
		ThreadErstellenDurchImplementRunnable tr=new ThreadErstellenDurchImplementRunnable();
		
		te.start();
		tr.run();

	}

}
