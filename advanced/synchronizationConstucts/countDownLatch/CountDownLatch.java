package advanced.synchronizationConstucts.countDownLatch;

public class CountDownLatch {

	int count = 0;
	
	public CountDownLatch(int count) {
		this.count = count;
	}
	
	public synchronized void await() throws InterruptedException {
		
		while(count > 0) {
			this.wait();
		}
	} 
	
	public synchronized void countDown() throws InterruptedException {
		
		count--;
		
		if(count == 0) {
			this.notifyAll();
		}
	}
}
