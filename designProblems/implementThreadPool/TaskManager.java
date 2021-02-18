package designProblems.implementThreadPool;


import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class TaskManager implements Runnable, TaskManagerInterface {
	
	private Queue taskQueue;
	
	private TaskStatus taskStatus = null;
	private Thread currentThread = null;
	
	private Task currentTask = null;
	private volatile boolean isStopped = false;

	
	public TaskManager(Queue queue, TaskStatus taskStatus) {
		
		taskQueue = queue;
		this.taskStatus = taskStatus;
		
	}
	
	public  void run() {
		
		this.currentThread = Thread.currentThread();
		
		while(!this.isStopped ) {
			
			while(taskStatus.getPendingTaskcount() == 0 && !this.isStopped)
				suspendCurrentThread();
				
			System.out.println(this.currentThread.getName()+" is waiting to perform a new Task.");
			
				
			
			//try {
				
				
				//Task task = (Task)taskQueue.poll(1,TimeUnit.SECONDS);
				Task task = (Task)taskQueue.poll();
				
				if(task != null) {
					this.currentTask = task;
					System.out.println(this.currentThread.getName()+" has picked up task "+currentTask.getName());
					task.run();
					
					if(currentTask.isCompleted) {
						System.out.println(this.currentThread.getName()+" has completed task "+currentTask.getName());
						this.taskStatus.setCompletedTaskcount(this.taskStatus.getCompletedTaskcount() + 1); 
						this.taskStatus.setPendingTaskcount(this.taskStatus.getPendingTaskcount() - 1);
					}else {
						this.taskStatus.setStoppedTaskcount(this.taskStatus.getStoppedTaskcount() + 1);
						this.taskStatus.setPendingTaskcount(this.taskStatus.getPendingTaskcount() - 1);
					}
				}
				
			/*} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//this.isStopped = true;
				System.out.println(this.currentThread.getName()+" executing task "+currentTask.getName()+" has been interrupted.");
				
			}*/
		}
		
		System.out.println(this.currentThread.getName()+" has stopped executing futher tasks.");
		
		
	}
	
	public 	void enqueueTask(Task task) {
		
		//try {
			
			this.taskQueue.add(task);
			System.out.println("Task "+ task.getName() + " has been queued");
			taskStatus.setPendingTaskcount(taskStatus.getPendingTaskcount() + 1);
			awakenAllThreads();
			
		/*} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}	 
	
	public  void stopTask(){
		
		this.isStopped = true;
		
		if(!currentTask.isCompleted)
			System.out.println(this.currentThread.getName()+" executing task "+currentTask.getName()+" is being gracefully stopped.");
		else
			System.out.println(this.currentThread.getName()+" waiting for new tasks is being gracefully stopped.");
	}
	
	public  void startTask(){
		
		this.isStopped = false;
		
		if(!currentTask.isCompleted) {
			
			System.out.println(this.currentThread.getName()+" is restarting to execute task "+currentTask.getName());
		}
		else
			System.out.println(this.currentThread.getName()+" waiting for new tasks is being gracefully stopped.");
	}
	
	public void cancelTask(){
		
		this.isStopped = true;
		
		if(!currentTask.isCompleted)
			System.out.println(this.currentThread.getName()+" executing task "+currentTask.getName()+" is being forcefully stopped.");
		else
			System.out.println(this.currentThread.getName()+" waiting for new tasks is being forcefully stopped.");

		this.currentThread.interrupt();
		
		// if the task was interruped before completion then reenqueue the task
		if(!currentTask.isCompleted)
			enqueueTask(currentTask);
	}
	
	public void suspendCurrentThread() {
		
		synchronized(taskQueue) {
		
			try {
				System.out.println(this.currentThread.getName()+" will go in a wait state.");
				taskQueue.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				taskQueue.notify();
			}
		}
	}
	
	public void awakenCurrentThread() {
		
		synchronized(taskQueue) {
			System.out.println(this.currentThread.getName()+" has woken up.");
			taskQueue.notify();
		}
		
	}
	
	public void awakenAllThreads() {
		
		synchronized(taskQueue) {
			System.out.println(this.currentThread.getName()+" is woking up all threads.");
			taskQueue.notifyAll();
		}
		
	}
	
	public synchronized  boolean isStopped() { 
	
		return  this.isStopped; 
	}
	
	public Thread getWorkerThread() {
		return this.currentThread ;
	}
	
	public Task getTask() {
		return this.currentTask ;
	}

}
