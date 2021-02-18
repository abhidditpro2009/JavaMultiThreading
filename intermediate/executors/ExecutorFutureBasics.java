package intermediate.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class ExecutorFutureBasics {

	public static void main(String args[]) {
		
		// create a runnable task. This implements the Runnable interface.
		// Below is the functional interface in lambda form
		// This task doesn't return anything
		
		Runnable runnableTask = () -> 	{ 
											try {
												Thread.sleep(5000);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											System.out.println(Thread.currentThread().getName() + " is executing runnable Task");
											
										};
										
		// create a callable task. This implments a callable interface.
		// It has a return type of "String"
										
		Callable<String> callableTask = () -> 	{ 
			System.out.println(Thread.currentThread().getName() + " is executing callable Task");
			Thread.sleep(5000);
			return "Hello callableTask";
		};
										
		
		// We will now create a cachedTreadPool using executor service. This will create new threads as required and will recycle unused threads
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// We will submit both tasks to the threadPool. The submit method can accept tasks of both callable and runnable interface. 
		// The return type of callable interface can be consumed by a Future interface. This basically gives us a handle for task management.
		// Using future interface task can be cancelled. It status can be checked by isDone() and isCancelled(). 
		// We can also block main thread to wait till task completes using get(). The result of the task will be returned by get().
		
		executor.submit(runnableTask);
		Future<String> future1 = executor.submit(callableTask);
		
		System.out.println(future1.isDone());
		
		try {
			String result = future1.get();
			System.out.println(result);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// What if we want runnable interface to return something. we can use the FutureTask interface and make it return something.
		// The FutureTask is similar to the Future interface and helps with task management.
		
		FutureTask<String> futureTask = new FutureTask<>(runnableTask,"Hello runnableTask");
		executor.submit(futureTask);
		try {
			
			String result = futureTask.get();
			System.out.println(result);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

