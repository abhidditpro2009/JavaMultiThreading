package designProblems.implementLock;

public class SimpleLock implements LockInterface {

	boolean isLocked;
	
	public SimpleLock(){

		isLocked = false;
	}
	
	public void lock() {
		
		synchronized(this) {
			
			while(isLocked) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			isLocked = true;
		}
	}
	
	public void unlock() {
		
		synchronized(this) {
			
			isLocked = false;
			this.notifyAll();
		}
	}
}
