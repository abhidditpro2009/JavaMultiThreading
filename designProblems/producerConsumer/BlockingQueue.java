package designProblems.producerConsumer;

public class BlockingQueue {

	int[] queue;
	int front = -1;
	int rear = -1;
	int count = 0;
	int capacity = 0;
	
	public BlockingQueue(int n) {
		
		queue = new int[n];
		capacity = n;
	}
	
	public synchronized void add(int x) throws InterruptedException {
		
		rear++;
		
		while(count == capacity) {
			System.out.println("Queue is full");
			System.out.println("Thread "+ Thread.currentThread().getName() + " is blocked");
			wait();
		}
		
		if(rear == capacity)
			rear = 0;
		
		queue[rear] = x;
		count++;
		notifyAll();
	}
	
	public synchronized int poll() throws InterruptedException {
		
		front++;
		
		while(count == 0) {
			System.out.println("Queue is empty");
			System.out.println("Thread "+ Thread.currentThread().getName() + " is blocked");
			wait();
		}
		
		if(front == capacity)
			front = 0;
		
		int x = queue[front] ;
		count--;
		notifyAll();
		
		return x;
	}
}
