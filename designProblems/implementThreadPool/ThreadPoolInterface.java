package designProblems.implementThreadPool;

import java.util.List;

public interface ThreadPoolInterface {
	public 	void executeAllTasks(List<Task> tasks) ;
	public 	void stopAllTasks() ;
	public 	void cancelAllTasks() ;
	public 	void waitToFinishAllTasks() ;
	public  int getCompletedTaskcount() ;
	public  int getStoppedTaskcount() ;
	public  int getPendingTaskcount() ;
	public  int getEnqueuedTaskcount() ;
}