package designProblems.implementThreadPool;

public interface TaskManagerInterface {

	public 	void enqueueTask(Task task) ;
	public  void stopTask();
	public  void startTask() ;
	public  void cancelTask() ;
	public  boolean isStopped() ;
}
