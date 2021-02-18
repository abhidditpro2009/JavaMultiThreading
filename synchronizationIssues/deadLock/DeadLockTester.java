package synchronizationIssues.deadLock;

public class DeadLockTester {
	
	Object lock1 = new Object();
	Object lock2 = new Object();
	
	boolean deadlock = true; 
	
	public static void main(String[] args) throws InterruptedException {

		DeadLockTester test = new DeadLockTester();
		
		
		
		Thread t1 = new Thread( () -> { test.criticalSection(); }, "Thread1");
		
		Thread t2 = new Thread( () -> {	test.secondCriticalSection(); }, "Thread2");
		
		
		t1.start();
		t2.start();
				
		t1.join();
		t2.join();

		
		System.out.println("Main Thread finished");
	}
	
	
	
	public void criticalSection() {
		
		// Here the code will go into a deadlock because both threads are obtaining locks in different order and then get into deadlock 
		// because they are waiting on each other to release the lock
		
		if(deadlock) {
			
			synchronized(lock2) {
					
				System.out.println("Thread name: "+ Thread.currentThread().getName() +"Lock 2");
					
	
				synchronized(lock1) {
					
					System.out.println("Thread name: "+ Thread.currentThread().getName() +"Lock 1");
						
				}
			}
		}else {
			
			System.out.println("Since the locks are in same order. The two thread will not get into a deadlock");
			
			synchronized(lock1) {
				
				System.out.println("Thread name: "+ Thread.currentThread().getName() +"Lock 1");
					
	
				synchronized(lock2) {
					
					System.out.println("Thread name: "+ Thread.currentThread().getName() +"Lock 2");
						
				}
			}
		}
		
	}
	
	public  void secondCriticalSection() {
		
		synchronized(lock1) {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +"Lock 2");
			
			synchronized(lock2) {
				
				System.out.println("Thread name: "+ Thread.currentThread().getName() +"Lock 1");
					
			}

		}
	}
	
}
