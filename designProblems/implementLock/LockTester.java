package designProblems.implementLock;

public class LockTester {
	
	LockInterface lock = null;
	
	int lockMode = 1; // 0-> Unguarded, 1 -> Simple, 2-> ReEntrant, 3-> Fair, 4 -> FairReEntrant
	
	public static void main(String[] args) throws InterruptedException {

		LockTester test = new LockTester();
		
		test.init();
		
		Thread t1 = new Thread(test::criticalSection, "Thread1");
		
		Thread t2 = new Thread(test::criticalSection, "Thread2");
		
		Thread t3 = new Thread(test::criticalSection, "Thread3");
		
		Thread t4 = new Thread(test::criticalSection, "Thread4");
		
		Thread t5 = new Thread(test::criticalSection, "Thread5");
		
		Thread t6 = new Thread(test::criticalSection, "Thread6");
		
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
	
	public void init() {
		
		System.out.println("Current lock Mode: "+ lockMode);
		
		switch(lockMode) {
			case 1:
				lock =  new SimpleLock();
				break;
			case 2:
				lock =  new ReEntrantLock();
				break;
			case 3:
				lock =  new FairLock();
				break;
			case 4:
				lock =  new FairReEntrantLock();
				break;
			default:
				lock =  new SimpleLock();
				break;
		
		}
	}
	
	public  void criticalSection() {
		
		if(lockMode != 0)
			lock.lock();
			
		for(int i=0;i<10;i++) {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +" Task "+i);
			
			if(lockMode == 2 || lockMode == 4) {
				secondCriticalSection();
			}
		}
		
		if(lockMode != 0) {
			lock.unlock();
		}
	}
	
	public  void secondCriticalSection() {
		
		if(lockMode != 0) {
			lock.lock();
		}

		System.out.println("Thread name: "+ Thread.currentThread().getName() +" Thread priority "+ Thread.currentThread().getPriority());
		
		if(lockMode != 0) {
			lock.unlock();
		}
	}
}
