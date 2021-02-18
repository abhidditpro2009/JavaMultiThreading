package intermediate.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Tester {
	
	static Runnable runnableTask ;
	static Callable<String> callableTask ;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Tester test = new Tester();
		
		test.init();
		
		test.testGetCall();
		
		 
		
	}

	public void init() {
		
		// create a runnable task. This implements the Runnable interface.
		// Below is the functional interface in lambda form
		// This task doesn't return anything
		
		runnableTask = () -> 	{ 
									System.out.println(Thread.currentThread().getName() + " has started to execute runnable Task");
									
									try {
										Thread.sleep(5000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									System.out.println(Thread.currentThread().getName() + " has completed to execute runnable Task");
									
								};
										
		// create a callable task. This implments a callable interface.
		// It has a return type of "String"
										
		callableTask = () -> 	{ 
			
									System.out.println(Thread.currentThread().getName() + " has started to execute callable Task");
									
									Thread.sleep(5000);
									
									System.out.println(Thread.currentThread().getName() + " has completed to execute callable Task");
									
									return "Hello callableTask";
								};
	}
	
	public void testGetCall() {
		
		CompletableFuture cmpFuture =  CompletableFuture.runAsync(runnableTask);
		
		try {
			
			cmpFuture.get();
			System.out.println("Blocking get complete for runnable task");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		cmpFuture =  CompletableFuture.supplyAsync((Supplier<String>)callableTask);
	}
}


