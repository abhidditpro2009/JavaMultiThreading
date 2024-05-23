package Basics.interruptThread;

public class interruptThread implements Runnable {

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " started");
		
		try {
			
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " finished");
			
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrupted");
		}
	}

	
	public static void main(String[] args) {
		
		
		interruptThread interruptedRunnable = new interruptThread();
		
		
		Thread thread1 = new Thread(interruptedRunnable);
		thread1.start();
		
		Thread thread2 = new Thread(interruptedRunnable);
		thread2.start();
		
		Thread thread3 = new Thread(interruptedRunnable);  
		thread3.start();
		
		Thread thread4 = new Thread(interruptedRunnable);
		thread4.start();
		
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
		thread4.interrupt();
		
		// let these threads finish before main thread
		
		try {
			
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Main Thread finished");
	}
}
