package designProblems.implementCallback;

public class CallbackTester {

	public static void main(String args[]) {
		
		CallbackInterface callback = new Callback();
		
		SynchronousCallback sync = new SynchronousCallback(callback);
		sync.performCallback();
		
		AsynchronousCallback async = new AsynchronousCallback(callback);
		async.performCallback();
	}
}
