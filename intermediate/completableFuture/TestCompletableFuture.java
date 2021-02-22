package intermediate.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class TestCompletableFuture {
	
	static Runnable runnableTask ;
	static Callable<String> callableTask ;
	static Supplier<String> supplierTask;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestCompletableFuture test = new TestCompletableFuture();
		
		test.init();
		
		test.testSupplierTask();
		
		 
		
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
								
		supplierTask = () -> 	{ 
									
									System.out.println(Thread.currentThread().getName() + " has started to execute supplier Task");
									
									try {
										
										Thread.sleep(5000);
										System.out.println(Thread.currentThread().getName() + " has completed to execute supplier Task");
										
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
									return "Hello callableTask";
								};
	}
	
	public void testRunnableTask() {
		
		// A runnable Task can be run using runAsync method of completable Future
		
		CompletableFuture<Void> cmpFuture =  CompletableFuture.runAsync(runnableTask);
		
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
		
	}
	
	public void testCallableTask() {
		
		// A runnable Task can be run using runAsync method of completable Future
		
		
		
		try {
			CompletableFuture<String> cmpFuture =  CompletableFuture.supplyAsync((Supplier<String>) callableTask);
		}
		catch(Exception e) {
			System.out.println("Callable interface does not work with completable future. Use supplier interface instead");
		}
	}
	
	public void testSupplierTask() {
		
		// A runnable Task can be run using runAsync method of completable Future
		
		CompletableFuture<String> cmpFuture =  CompletableFuture.supplyAsync(supplierTask);
		
		try {
			
			cmpFuture.get();
			System.out.println("Blocking get complete for supplier task");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


