package designProblems.implementThreadPool;

public class TaskStatus {
	private  volatile int taskStoppedCount ;
	private  volatile int taskPendingCount ;
	private  volatile int taskCompletedCount ;

	TaskStatus(){
		taskStoppedCount = 0;
		taskPendingCount = 0;
		taskCompletedCount = 0;
	}
	
	public  int getCompletedTaskcount() {
		return taskCompletedCount;
	}
	
	public  void setCompletedTaskcount(int count) {
		 taskCompletedCount = count;
	}
	
	public  int getStoppedTaskcount() {
		return taskStoppedCount;
	}
	
	public  void setStoppedTaskcount(int count) {
		taskStoppedCount = count;
	}
	
	public  int getPendingTaskcount() {
		return taskPendingCount;
	}
	
	public  void setPendingTaskcount(int count) {
		taskPendingCount = count;
	}
}
