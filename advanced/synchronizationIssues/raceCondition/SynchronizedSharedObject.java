package advanced.synchronizationIssues.raceCondition;

public class SynchronizedSharedObject {

	private  volatile int count = 0;

	public synchronized void setCount(int c) {
		System.out.println("name:"+ Thread.currentThread().getName());
		System.out.println("Previous value:"+this.getCount());
		count = c;
		System.out.println("name:"+ Thread.currentThread().getName());
		System.out.println("Updated value:"+this.getCount());
	}
	
	public synchronized int getCount() {
		return count;
	}
}
