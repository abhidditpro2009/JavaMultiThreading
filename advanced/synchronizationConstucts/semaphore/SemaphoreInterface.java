package advanced.synchronizationConstucts.semaphore;

public interface SemaphoreInterface {

	public void acquire();
	
	public void release() throws InterruptedException;
}
