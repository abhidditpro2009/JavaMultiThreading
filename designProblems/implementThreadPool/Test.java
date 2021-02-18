package designProblems.implementThreadPool;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		Test test = new Test();
		
		ThreadPool threadPool = new ThreadPool(Constants.PRIORITY_QUEUE,4,100);
		
		/*threadPool.executeTask(() -> {System.out.println("Hello World 1"); Thread.sleep(5000);},"task1");
		threadPool.executeTask(() -> {System.out.println("Hello World 2");},"task2");
		threadPool.executeTask(() -> {System.out.println("Hello World 3");},"task3");*/
		
		List<Task> taskList = new ArrayList<Task>();
		
		// create new tasks
		
		for(int i=0;i<20;i++) {
			Task task = new Task("task"+i);
			task.setPriority(20-i);
			task.setTaskPayload(()->{ System.out.println(" Hello World ");});
			taskList.add(task);
		}
		
		// execute all tasks
		
		threadPool.executeAllTasks(taskList);
		
		// wait for all task to finish. Start a new thread.
		
		Thread finishAllTasks = new Thread(() -> {threadPool.waitToFinishAllTasks();});
		finishAllTasks.start();
		
		
		// suspend the main thread for 5 ms
		
		test.suspendMainThread();
		
		// cancel all tasks. Cancel will forcefully stop a task
		// Start the command a different thread.
		
		Thread cancelAllTasks = new Thread(() -> {threadPool.cancelAllTasks();});
		cancelAllTasks.start();
		
		
		
		
		//threadPool.startAllWorkerThreads();
		
		// suspend the main thread for 5 ms
		
		test.suspendMainThread();
		
		// start all tasks. Start in a different thread.
		
		Thread startAllTasks = new Thread(() -> {threadPool.startAllTasks();});
		startAllTasks.start();
		
		// suspend the main thread for 5 ms
		
		test.suspendMainThread();
		
		Thread finishAllTasks2 = new Thread(() -> {threadPool.waitToFinishAllTasks();});
		finishAllTasks2.start();
		
		// suspend the main thread for 5 ms
		
		/*test.suspendMainThread();
		
		Thread stopAllTasks = new Thread(() -> {threadPool.stopAllTasks();});
		stopAllTasks.start();*/
		
		try {
		
			//System.out.println("Joined");
			
			finishAllTasks.join();
			cancelAllTasks.join();
			startAllTasks.join();
			finishAllTasks2.join();
			//stopAllTasks.join(); 
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//threadPool.waitForTasksToFinish();
		//threadPool.cancelAllTasks();
		
		System.out.println("Tasks enqueued: "+threadPool.getEnqueuedTaskcount());
		System.out.println("Tasks completed: "+threadPool.getCompletedTaskcount());
		System.out.println("Tasks pending: "+threadPool.getPendingTaskcount());
		System.out.println("Tasks stopped: "+threadPool.getStoppedTaskcount());
		
		for(int i=20;i<40;i++) {
			Task task = new Task("task"+i);
			task.setPriority(40-i);
			task.setTaskPayload(()->{ System.out.println(" Hello Abhijeet ");});
			taskList.add(task);
		}
		
		threadPool.executeAllTasks(taskList);
		
	}
	
	public void suspendMainThread() {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
