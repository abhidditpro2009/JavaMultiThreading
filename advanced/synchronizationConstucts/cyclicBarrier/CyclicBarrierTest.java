package advanced.synchronizationConstucts.cyclicBarrier;

public class CyclicBarrierTest {

	CyclicBarrier barrier = new CyclicBarrier(3);
	
	public static void main(String[] args) throws InterruptedException {

		CyclicBarrierTest test = new CyclicBarrierTest();
		
		Thread t1 = new Thread(test::criticalSection, "Thread1");
		
		Thread t2 = new Thread(test::criticalSection, "Thread2");
		
		Thread t3 = new Thread(test::criticalSection, "Thread3");
		
		
		t1.start();
		t2.start();
		t3.start();
				
		t1.join();
		t2.join();
		t3.join();
		
		System.out.println();
		System.out.println("Test reusing the cyclic barrier");
		System.out.println();
		
		Thread t4 = new Thread(test::criticalSection, "Thread4");
		
		Thread t5 = new Thread(test::criticalSection, "Thread5");
		
		Thread t6 = new Thread(test::criticalSection, "Thread6");

		t4.start();
		t5.start();
		t6.start();
				
		t4.join();
		t5.join();
		t6.join();
		
		
		System.out.println("Main Thread finished");
	}
	
	public void criticalSection() {
		
		System.out.println("Thread name: "+ Thread.currentThread().getName() +" has reached the barrier");
		
		try {
			barrier.await();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		System.out.println("Thread name: "+ Thread.currentThread().getName() +" has crossed the barrier");
	}
	
	
}
