package advanced.synchronizationConstucts.cyclicBarrier;

public class CyclicBarrier {

	int count = 0;
	int released =0;
	int total =0;
	
	public CyclicBarrier(int total) {
		this.total = total;
	}
	
	public synchronized void await() throws InterruptedException {
		
		// This is important. We dont want any new threads to come in woken up by spurious wake up and increase the count. That might also result in deadlock. Hence we will block this.
		
		while(count== total)
			this.wait();
		
		// increment count of incoming threads
		count++;
		released = count;
		
		// all the threads will be blocked till desired count is reached.
		while(count < total) {
			
			System.out.println("Thread name: "+ Thread.currentThread().getName() +" is waiting at the barrier");
			
			
			this.wait();
		}
		
		// we also need to keep track of the notified threads. 
		
		
		// At this point all threads have reached the barrier and need to woken up.
		
		System.out.println("Thread name: "+ Thread.currentThread().getName() +" is notified.");
		this.notifyAll();
		
		// decrement release for the notified thread. 
		released--;
		
		System.out.println("Number of threads waiting at the barrier: "+ released);
		// We will set count to zero only when all threads have been notified. This is done so that the barrier can be reused.
		
		if(released == 0) {
			System.out.println("All threads have been notified.");
			count =0;
		}
	}
	
}
