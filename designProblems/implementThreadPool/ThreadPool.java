package designProblems.implementThreadPool;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool implements ThreadPoolInterface {

	private TaskStatus taskStatus = null;
	private Queue taskQueue = null;
	
	private List<TaskManager> taskList = new ArrayList<>();
	private List<Thread> workerThreadPool  = new ArrayList<>();
	
	private String queueType = "";
	private int noOfThreads ;
	private volatile boolean isThreadPoolStopped = false;
	private volatile int taskEnqueCount = 0;
	
	
	public ThreadPool(String queueType,int noOfThreads, int maxNoOfTasks) {
		
		this.queueType = queueType;
		
		QueueFactory queueFactory = new QueueFactory();
		//taskQueue = new ArrayBlockingQueue(maxNoOfTasks);
		
		taskQueue = queueFactory.getQueue(queueType, maxNoOfTasks);
		
		taskStatus = new TaskStatus();
		this.noOfThreads = noOfThreads;
		
		// Create task handlers and add all them to the list.
		// When we add tasks to the taskQueue. The worker threads will deque them and run the handlers.
		
		for(int i= 0;i < noOfThreads;i++) {
			
			
			// create handlers for incoming tasks.
			TaskManager taskHandle = new TaskManager(taskQueue,taskStatus); 
			taskList.add(taskHandle);
			
			Thread workerThread = new Thread(taskHandle,"Worker Thread: "+i);
			System.out.println(workerThread.getName()+" started.");
			
			workerThreadPool.add(workerThread);
			workerThread.start();
			
		}
		
	}
	
	public synchronized void executeAllTasks(List<Task> tasks) {
		
		
		
		if(this.isThreadPoolStopped) { 
			System.out.println("ThreadPool is stopped");
			return;
		}
		
		for(int i=0;i<tasks.size();i++) {
			 
			 taskList.get(0).enqueueTask(tasks.get(i));
		}
		
		
		taskEnqueCount++;
        	
	}
	
	private void startAllWorkerThreads() {
		
		int size = noOfThreads - workerThreadPool.size();
		
		for(int i= 0;i < size;i++) {
			
			
			Thread workerThread = new Thread(taskList.get(i),"New Worker Thread: "+i);
			System.out.println("New worker thread: "+workerThread.getName()+" started.");
			
			workerThreadPool.add(workerThread);
			workerThread.start();
			
		}
		
	}
	
	public synchronized void startAllTasks() {
		
		if(this.isThreadPoolStopped) {
			
			
			this.isThreadPoolStopped = false;
			System.out.println(Thread.currentThread().getName()+ " is starting all tasks in Thread Pool");
			
			
			
			for(int i=0;i<taskList.size();i++ ) {
				
				TaskManager taskManager = taskList.get(i);
				
				
				taskManager.startTask();
			}
			
			startAllWorkerThreads();
		}
		
		//System.out.println("Thread pool is not stoped");
		
		
	}
	
	public synchronized void stopAllTasks() {
		
		this.isThreadPoolStopped = true;
		System.out.println(Thread.currentThread().getName()+ " is stopping all tasks in Thread Pool");
		
		for(TaskManager taskManager : taskList ) {
			taskManager.stopTask();
			workerThreadPool.remove(taskManager.getWorkerThread());
			System.out.println(taskManager.getWorkerThread().getName()+ " is removed from the Thread Pool");
		}
		
	}
	
	public synchronized void cancelAllTasks() {
		
		this.isThreadPoolStopped = true;
		System.out.println(Thread.currentThread().getName()+ " is cancelling all tasks in Thread Pool");
		
		for(TaskManager taskManager : taskList ) {
			taskManager.cancelTask();
			workerThreadPool.remove(taskManager.getWorkerThread());
			System.out.println(taskManager.getWorkerThread().getName()+ " is removed from the Thread Pool");
		}
		
	}
	
	public void waitToFinishAllTasks() {
		
		while(!this.isThreadPoolStopped && taskStatus.getPendingTaskcount() != 0) {
						
			System.out.println("Waiting for Threads to finish "+taskStatus.getPendingTaskcount()+ " pending tasks");

			
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	public  int getCompletedTaskcount() {
		
		return taskStatus.getCompletedTaskcount();
	}
	

	
	public  int getStoppedTaskcount() {
		
		return taskStatus.getStoppedTaskcount();
	}
	
	
	
	public  int getPendingTaskcount() {
		
		return taskStatus.getPendingTaskcount();
	}
	
	
	public  int getEnqueuedTaskcount() {
		
		return taskEnqueCount;
	}

}
