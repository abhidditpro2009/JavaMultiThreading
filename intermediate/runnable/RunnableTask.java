package intermediate.runnable;

public class RunnableTask implements Runnable{

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " is starting to execute runnable Task");
		
		try {
			Thread.sleep(5000);
			
			System.out.println(Thread.currentThread().getName() + " has finished executing runnable Task");
			
		} catch (InterruptedException e) {
			
			System.out.println(Thread.currentThread().getName() + " has been interrupted while executing runnable Task");
		}
	}
}
