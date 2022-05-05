package Semaphore;

public class Main {
	public static void main(String[] args) {
		Semaphore s = new Semaphore(4);
		
		var t1=new Thread(()->{
			System.out.println("t1: start");
			try {
				s.acquire();
				System.out.println("t1: work");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t1: end");
			s.release();
		});
		
		var t2=new Thread(()->{
			System.out.println("t2: start");
			try {
				s.acquire();
				System.out.println("t2: work");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t2: end");
			s.release();
		});
		
		var t3=new Thread(()->{
			System.out.println("t3: start");
			try {
				s.acquire();
				System.out.println("t3: work");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t3: end");
			s.release();
		});
		
		var t4=new Thread(()->{
			System.out.println("t4: start");
			try {
				s.acquire();
				System.out.println("t4: work");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t4: end");
			s.release();
		});
		
		var t5=new Thread(()->{
			System.out.println("t5: start");
			try {
				s.acquire();
				System.out.println("t5: work");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("t5: end");
			s.release();
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
