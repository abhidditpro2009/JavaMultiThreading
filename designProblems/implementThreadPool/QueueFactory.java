package designProblems.implementThreadPool;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueFactory {

	private Queue<Task> blockingQueue = null;
	private Queue<Task> priorityQueue = null;
	
	public Queue getQueue(String type, int capacity) {
		
		blockingQueue = new ArrayBlockingQueue<Task>(capacity);

		Comparator<Task> taskSorter = Comparator.comparing(Task::getPriority);
		priorityQueue = new PriorityQueue<Task>(capacity,taskSorter);
		
		if(type.equals("blocking")) {
			return blockingQueue;
		}
		 
		if(type.equals("priority")) {
			return priorityQueue;
		}
		
		return null;
	}
}
