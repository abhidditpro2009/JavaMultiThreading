package designProblems.implementCallback;

public class SynchronousCallback {

	private CallbackInterface callbackInteface;
	
	SynchronousCallback(CallbackInterface callbackInteface){
	
		this.callbackInteface = callbackInteface;
	}
	
	public void performCallback() {
		
		System.out.println("Inside synchronous callback");
		callbackInteface.callbackMethod();
	}
}
