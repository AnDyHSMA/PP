package semaphore_serhat;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Serhat Göl
 *
 *
 * Class to realize semaphore
 * Semaphore has a counter which possibly can incremented or decremented by threads.
 * The thread which decrementing the counter to zero will be blocked.
 */
public class Semaphore {
	/**
	 * Defines the amount of free remaining thread places
	 */
	private volatile int remainingProcesses;

	/**
	 * 
	 * Basic constructor
	 * 
	 * @param permitCount counter min 0 
	 */
	public Semaphore(int permitCount) {
		try {
			setRemainingProcesses(permitCount);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * Trivial getter
	 *  
	 * @return remainingProcesses
	 */
	public int getRemainingProcesses() {
		return this.remainingProcesses;
	}

	/**
	 * 
	 * Trivial setter
	 * 
	 * @param remainingProcesses
	 */
	private void setRemainingProcesses(int remainingProcesses) throws Exception {
		if(remainingProcesses < 0)
			throw new Exception("The number must higher than 0!");
			
		this.remainingProcesses = remainingProcesses;
	}
	
	/**
	 * Reduce the remaining processes if higher than zero or block thread
	 */
	public synchronized void acquire() {
		while(getRemainingProcesses() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		if(getRemainingProcesses() > 0){
			try {
				setRemainingProcesses(getRemainingProcesses() - 1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	/**
	 * Enlarge the remaining processes if higher than zero and notify thread if needed
	 */
	public synchronized void release() {
		if(getRemainingProcesses() == 0){
			try {
				setRemainingProcesses(getRemainingProcesses() + 1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			notifyAll();
			
		}else {
			try {
				setRemainingProcesses(getRemainingProcesses() + 1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	


	
	/**
	 * 
	 * Main method to execute code.
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		Semaphore processes = new Semaphore(4); 

		SampleThread thread1 = new SampleThread(processes);
		thread1.setName("Thread1");
		thread1.start();

		SampleThread thread2 = new SampleThread(processes);
		thread2.setName("Thread2");
		thread2.start();
		
		SampleThread thread3 = new SampleThread(processes);
		thread3.setName("Thread3");
		thread3.start();
		
		SampleThread thread4 = new SampleThread(processes);
		thread4.setName("Thread4");
		thread4.start();
		
		SampleThread thread5 = new SampleThread(processes);
		thread5.setName("Thread5");
		thread5.start();
		
		SampleThread thread6 = new SampleThread(processes);
		thread6.setName("Thread6");
		thread6.start();
		
		SampleThread thread7 = new SampleThread(processes);
		thread7.setName("Thread7");
		thread7.start();

		SampleThread thread8 = new SampleThread(processes);
		thread8.setName("Thread8");
		thread8.start();

		SampleThread thread9 = new SampleThread(processes);
		thread9.setName("Thread9");
		thread9.start();


		SampleThread thread10 = new SampleThread(processes);
		thread10.setName("Thread10");
		thread10.start();


		SampleThread thread11 = new SampleThread(processes);
		thread11.setName("Thread11");
		thread11.start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.err.println(processes.getRemainingProcesses());
		
		
		
		
	}

}
/**
 * 
 * Thread class to demonstrate the semaphore
 *
 */
class SampleThread extends Thread {
	/**
	 * Semaphore object
	 */
	Semaphore processes;
	
	/**
	 * basic constructor with the semaphore object as parameter
	 * @param processes
	 */
	public SampleThread(Semaphore processes){
		this.processes = processes;
		
	}
	
	/**
	 * Our run method which includes a side effect to underline the 
	 * process of "queue handling" the thread pool
	 */
	public void run() {
		processes.acquire();
		System.out.println(processes.getRemainingProcesses());

		System.out.println("Thread -> " + Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		processes.release();
		System.out.println(processes.getRemainingProcesses());
		
	}

}
