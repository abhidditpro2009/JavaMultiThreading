package designProblems.implementThreadPool;

public class Task implements Runnable {
	
	String taskName = "";
	TaskPayload payload = null; 
	boolean isCompleted = false; 
	int priority = 0;
	int delay = 0;
	
	public void defaultTaskPayload() {
		
		System.out.println(Thread.currentThread().getName()+" is executing task "+this.getName());
		
		
		// long running task
		
		try {
			
			Thread.sleep(Constants.TASK_EXECUTION_TIME);
			
			System.out.println(Thread.currentThread().getName()+" has completed task "+this.getName());
			isCompleted = true;
			
		} catch (InterruptedException e) {
			
			//e.printStackTrace();
			isCompleted = false;
			System.out.println(Thread.currentThread().getName()+" executing task "+this.getName()+" has been forcefully interrupted.");
		}
	}
	
	public void setTaskPayload(TaskPayload payload) {
		
		this.payload = payload;
	}
	
	public TaskPayload getTaskPayload() {
		return this.payload;
	}
	
	public Task(String taskName){
		
		this.taskName = taskName;
		setTaskPayload(this::defaultTaskPayload); 
	}
	
	public Task(String taskName, int priority){
		
		this.taskName = taskName;
		this.priority = priority;
		setTaskPayload(this::defaultTaskPayload);
	}
	
	public Task(String taskName, int priority, int delay){
		
		this.taskName = taskName;
		this.priority = priority;
		this.delay = delay;
		setTaskPayload(this::defaultTaskPayload);
	}
	
	public Task(String taskName, int priority, int delay, TaskPayload payload){
		
		this.taskName = taskName;
		this.priority = priority;
		this.delay = delay;
		//this.payload = (new TaskPayload()).getTaskPayloadManager();
		setTaskPayload(payload);
	}
	
	
	public String getName() {
		
		return taskName;
	}
	
	public void setName(String taskName) {
		
		this.taskName = taskName;
	}
	
	public int getPriority() {
		
		return priority;
	}
	
	public void setPriority(int priority) {
		
		this.priority = priority;
	}
	
	public int getDelay() {
		
		return delay;
	}
	
	public void setDelay(int delay) {
		
		this.delay = delay;
	}
	
	public boolean isTaskCompleted() {
		
		return isCompleted;
	}
	
	public void run() {
		
		System.out.println(Thread.currentThread().getName()+" is executing task "+this.getName());
		
		
		// long running task
		
		try {
			payload.runTaskPayload();
			Thread.sleep(Constants.TASK_EXECUTION_TIME);
			
			System.out.println(Thread.currentThread().getName()+" has completed task "+this.getName());
			isCompleted = true;
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			isCompleted = false;
			System.out.println(Thread.currentThread().getName()+" executing task "+this.getName()+" has been forcefully interrupted.");
		}

	}

}
