package designProblems.producerConsumer;

public class ProducerConsumer {

	//BlockingQueue queue = new BlockingQueue(10);
	NonBlockingQueue queue = new NonBlockingQueue(10);
	volatile int count=0;
	
	public static void main(String args[]) throws InterruptedException {
		
		ProducerConsumer test = new ProducerConsumer();
		
		Thread producer1 = new Thread( () -> { test.produce(); }, "Producer1");
		
		Thread producer2 = new Thread( () -> {	test.produce(); }, "Producer2");
		
		Thread producer3 = new Thread( () -> {	test.produce(); }, "Producer3");
		
		Thread producer4 = new Thread( () -> {	test.produce(); }, "Producer4");
		
		Thread producer5 = new Thread( () -> {	test.produce(); }, "Producer5");
		
		Thread consumer1 = new Thread( () -> { test.consume(); }, "Consumer1");
		
		Thread consumer2 = new Thread( () -> {	test.consume(); }, "Consumer2");
		
		Thread consumer3 = new Thread( () -> {	test.consume(); }, "Consumer3");
		
		producer1.start();
		producer2.start();
		producer3.start();
		producer4.start();
		producer5.start();
		
		consumer1.start();
		consumer2.start();
		consumer3.start();
				
		producer1.join();
		producer2.join();
		producer3.join();
		producer4.join();
		producer5.join();
		
		consumer1.join();
		consumer2.join();
		consumer3.join();
		
		System.out.println("Main Thread finished");
	}
	
	public  void produce() {

		for(int i=0;i<5;i++) {

			count = count+i;
			System.out.println("Thread "+ Thread.currentThread().getName()+" attempting to add " +count+" to the queue");
			queue.add(count);
		}
	}
	
	public  void consume() {

		for(int i=0;i<5;i++) {

			System.out.println("Thread "+ Thread.currentThread().getName()+ " attempting to remove "+ queue.poll() +" from the queue" );
		}
	}
}