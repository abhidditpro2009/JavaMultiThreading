package synchronizationConstucts.semaphore;

import designProblems.implementLock.LockTester;

public class SempahoreTester {

	BoundedSemaphore semaphore = new BoundedSemaphore(5);
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		SempahoreTester test = new SempahoreTester();
		
		Thread t1 = new Thread( () -> { test.criticalSection(); }, "Thread1");
		
		Thread t2 = new Thread( () -> {	test.criticalSection(); }, "Thread2");
		
		Thread t3 = new Thread( () -> { test.criticalSection(); }, "Thread3");
		
		Thread t4 = new Thread( () -> { test.criticalSection(); }, "Thread4");
		
		Thread t5 = new Thread( () -> {	test.criticalSection(); }, "Thread5");
		
		Thread t6 = new Thread( () -> { test.criticalSection(); }, "Thread6");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();

		
		System.out.println("Main Thread finished");
	}
	
	public  void criticalSection() {
		
		semaphore.acquire();
			
		for(int i=0;i<10;i++) {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +" Task "+i);
			
		}
		
		try {
			semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
