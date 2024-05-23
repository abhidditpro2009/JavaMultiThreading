package designProblems.implementCallback;

public class AsynchronousCallback {
	private CallbackInterface callbackInteface;
	
	AsynchronousCallback(CallbackInterface callbackInteface){
		this.callbackInteface = callbackInteface;
	}
	
	public void performCallback() {

		for(int i=0;i<10;i++) {
			new Thread(() -> callbackInteface.callbackMethod()).start();
		}
		
		System.out.println("Inside asynchronous callback");
	}
}
