package advanced.synchronizationConstucts.semaphore;

public class BinarySemaphore implements SemaphoreInterface{

	boolean signal = false;

	@Override
	public  void acquire() {

		signal = true;
		this.notify();
	}
	
	@Override
	public  void release() throws InterruptedException {
		
		synchronized(this) {
			
			while(!signal) {
				this.wait();
			}
			
			signal = false;
		}
	}
}
