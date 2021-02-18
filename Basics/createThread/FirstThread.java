package Basics.createThread;

public class FirstThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("Thread started");
	}

	
	public static void main(String[] args) {
		
		
		
		FirstThread obj = new FirstThread();
		Thread thread1 = new Thread(obj);
		thread1.start();
		
		Runnable runnable = new Runnable() { 
				public void run()
					{	System.out.println("Better thread started");
					} 
				
		};
		
		Thread thread2 = new Thread(runnable);
		thread2.start();
		
		Thread thread3 = new Thread( new Runnable() { 
				public void run()
					{	System.out.println("Even Better thread started");
					} 
				});
		thread3.start();
		
		Thread thread4 = new Thread( () -> 
					{ 
						System.out.println("Best thread started"); 
					});
		
		thread4.start();
		
		// let these threads finish before main thread
		
		try {
			
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main Thread finished");
		
	}
}


