package intermediate.futureTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import intermediate.runnable.RunnableTask;

public class TestFutureTask {

	public static void main(String[] args) {
	
		RunnableTask runnableTask = new RunnableTask();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		FutureTask<String> futureTask = new FutureTask<>(runnableTask,"Hello runnableTask");
		executor.submit(futureTask);
		
		System.out.println("Is task completed: "+futureTask.isDone());
		
		System.out.println("Block main thread till task completes");
		
		try {
		
			String result = futureTask.get();
			
			System.out.println(result);
			
		} catch (InterruptedException e) {
			
		} catch (ExecutionException e) {
			
		}
		
		System.out.println("Is task completed: "+futureTask.isDone());

	}

}
