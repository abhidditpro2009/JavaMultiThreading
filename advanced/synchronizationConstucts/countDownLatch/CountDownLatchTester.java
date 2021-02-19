package advanced.synchronizationConstucts.countDownLatch;


public class CountDownLatchTester {

	CountDownLatch latch = new CountDownLatch(3);
	
	public static void main(String[] args) throws InterruptedException {
	
		CountDownLatchTester test = new CountDownLatchTester();
		
		
		
		Thread t1 = new Thread( () -> { test.latch(); }, "Thread1");
		
		Thread t2 = new Thread( () -> {	test.latch(); }, "Thread2");
		
		Thread t3 = new Thread( () -> {	test.latch(); }, "Thread3");
		
		Thread t4 = new Thread( () -> {	test.countDown(); }, "Thread4");
		
		Thread t5 = new Thread( () -> {	test.countDown(); }, "Thread5");
		
		Thread t6 = new Thread( () -> {	test.countDown(); }, "Thread6");
		
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
	
	public void latch() {
		
		
		try {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +" has been latched");
			
			latch.await();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread name: "+ Thread.currentThread().getName() +" has been unlatched");
	}
	
	public void countDown() {
		
		
		try {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +" initiated countDown");
			
			latch.countDown();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
