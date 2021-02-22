package intermediate.supplier;

import java.util.function.Supplier;

public class SupplierTask implements Supplier<String> {

	@Override
	public String get() {
		// TODO Auto-generated method stub
		
		System.out.println(Thread.currentThread().getName() + " is starting to execute runnable Task");
		
		try {
			Thread.sleep(5000);
			
			System.out.println(Thread.currentThread().getName() + " has finished executing runnable Task");
			
		} catch (InterruptedException e) {
			
			System.out.println(Thread.currentThread().getName() + " has been interrupted while executing runnable Task");
			
		}
		
		return "Hello callableTask";
	}

}
