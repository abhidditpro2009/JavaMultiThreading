package advanced.synchronizationIssues.raceCondition;

public class RaceCondtion implements Runnable{

	SharedObject obj = null;
	SynchronizedSharedObject syncObj = null;
	boolean isSync = false;

	public RaceCondtion(Object obj, boolean isSync){
		
		this.isSync = isSync ;
		
		if(isSync)
			this.syncObj = (SynchronizedSharedObject)obj;
		else
			this.obj = (SharedObject)obj;
		
	}

	public void run(){
		
		
		for(int i=0;i<10;i++) {
			
			if(isSync)
				syncObj.setCount(syncObj.getCount()+1);
			else
				obj.setCount(obj.getCount()+1);
			
		}
	}

	
	public static void main(String[] args) {
		
		
		SharedObject sharedObj = new SharedObject();
		
		
		SynchronizedSharedObject syncSharedObj = new SynchronizedSharedObject();
		
		
		Thread t1 = new Thread(new RaceCondtion(sharedObj,false));
		Thread t2 = new Thread(new RaceCondtion(sharedObj,false));
		Thread t3 = new Thread(new RaceCondtion(sharedObj,false));
		Thread t4 = new Thread(new RaceCondtion(sharedObj,false));
		Thread t5 =new Thread(new RaceCondtion(sharedObj,false));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		try {
			
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			
		} catch (InterruptedException e) {
			
		}
		
		
		System.out.println();
		System.out.println();
		System.out.println("Lets fix the race condition using synchronization");
		System.out.println();
		
		Thread t6 =  new Thread(new RaceCondtion(syncSharedObj,true));
		Thread t7 = new Thread(new RaceCondtion(syncSharedObj,true));
		Thread t8 = new Thread(new RaceCondtion(syncSharedObj,true));
		Thread t9 = new Thread(new RaceCondtion(syncSharedObj,true));
		Thread t10 = new Thread(new RaceCondtion(syncSharedObj,true));
		
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		
		try {
			
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
			
		} catch (InterruptedException e) {
			
		}
	}
}
