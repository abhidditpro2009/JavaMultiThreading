package advanced.synchronizationConstucts.semaphore;

public class CountingSemaphore implements SemaphoreInterface {

	int count = 0;
	
	@Override
	public void acquire() {
		
		synchronized(this) {
			count++;
			this.notifyAll();
		}
	}

	@Override
	public void release() throws InterruptedException {
		
		synchronized(this) {
			
			while(count == 0) {
				this.wait();
			}
				
			count--;
		}
	}
}
