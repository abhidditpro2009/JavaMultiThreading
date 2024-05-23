package intermediate.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import intermediate.callable.CallableTask;

public class TestFuture {

	public static void main(String[] args) {

		CallableTask callableTask = new CallableTask();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Future<String> future = executor.submit(callableTask);
		
		System.out.println("Is task completed: "+future.isDone());
		
		System.out.println("Block main thread till task completes");
		
		try {
		
			String result = future.get();
			
			System.out.println(result);
			
		} catch (InterruptedException | ExecutionException e) {
			
		}
		
		System.out.println("Is task completed: "+future.isDone());
		
		executor.shutdown();
	}
}
