package designProblems.producerConsumer;

import java.util.concurrent.atomic.*;

public class NonBlockingQueue {

	int[] queue;
	AtomicInteger front;
	AtomicInteger rear;
	int capacity = 0;
	AtomicInteger count;
	AtomicBoolean lock ;
	
	public NonBlockingQueue(int n) {
		queue = new int[n];
		capacity = n;
		lock = new AtomicBoolean(false);
		count = new AtomicInteger(0);
		front = new AtomicInteger(-1);
		rear = new AtomicInteger(-1);
	}
	
	public  void add(int x)  {
		
		while(true) {
			
			while(!lock.compareAndSet(false, true)){
				//Thread.sleep(1);
			}
			
			rear.incrementAndGet();
			
			if(rear.get() == capacity) {
				rear.set(0);
			}
			
			if(count.get() < capacity) {

				System.out.println("Thread "+ Thread.currentThread().getName()+ " added "+ x +" to the queue" );
				queue[rear.get()] = x;
				count.incrementAndGet();
				lock.set(false);
				return;
				
			} else {
		
				System.out.println("Thread "+ Thread.currentThread().getName() + ": Queue is full");
				System.out.println("Thread "+ Thread.currentThread().getName() + " is blocked");
		
				lock.set(false);
			}
		}
	}
	
	public int poll()  {
		
		while(true) {
			while(!lock.compareAndSet(false, true)){
				//Thread.sleep(1);
			}
			
			front.incrementAndGet();
			
			if(front.get() == capacity)
				front.set(0);
			
			if(count.get() > 0){

				int x = queue[front.get()] ;
				System.out.println("Thread "+ Thread.currentThread().getName()+ " removed "+ x +" from the queue" );
				count.decrementAndGet();
				lock.set(false);
			
				return x;
				
			} else {
			
				System.out.println("Thread "+ Thread.currentThread().getName() + ": Queue is empty");
				System.out.println("Thread "+ Thread.currentThread().getName() + " is blocked");
			
				lock.set(false);
			
			}
		}
	}
}