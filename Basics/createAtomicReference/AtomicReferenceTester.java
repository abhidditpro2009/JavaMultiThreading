package Basics.createAtomicReference;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTester {

	public static void main(String args[]) throws InterruptedException {
		
		AtomicReference<String> reference = new AtomicReference<String>("Hello");
		
		
		
		System.out.println(reference.get());
		
		Thread thread1 = new Thread(() -> 	{ 
												
												System.out.println(reference.compareAndSet("Hello", "Hi1"));
												System.out.println(reference.get());
											}); 
		
		Thread thread2 = new Thread(() -> 	{ 
												System.out.println(reference.compareAndSet("Hi1","Hi2"));
												System.out.println(reference.get());
											}); 
		
		Thread thread3 = new Thread(() -> 	{ 
												System.out.println(reference.compareAndSet("Hi2","Hi3"));
												System.out.println(reference.get());
											});
		Thread thread4 = new Thread(() -> 	{ 
												System.out.println(reference.compareAndSet("Hi3","Hi4"));
												System.out.println(reference.get());
											}); 
		
		Thread thread5 = new Thread(() -> 	{ 
												System.out.println(reference.compareAndSet("Hi4","Hi5"));
												System.out.println(reference.get());
											}); 
		
		Thread thread6 = new Thread(() -> 	{ 
												System.out.println(reference.compareAndSet("Hi5","Hi6"));
												System.out.println(reference.get());
											}); 
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		thread6.join();
	}
}
