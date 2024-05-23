package advanced.synchronizationConstucts.semaphore;

public class BoundedSemaphore implements SemaphoreInterface {

	int count = 0;
	int max ;
	
	public BoundedSemaphore(int max) {
		this.max = max;
	}
	
	@Override
	public void acquire() {
		
		synchronized(this) {
			 while(count == max) {
				 try {
					 this.wait();
				 } catch (InterruptedException e) {
					 e.printStackTrace();
				 }
			 }
			 
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
			 this.notifyAll();
		}
	}
}
