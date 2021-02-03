package com.threads.start;

/*
 * In this example we will look into one of the two implementations of thread
 * We need to implement Runnable to the class 
 * While starting the Thread we need to pass the className to the Thread
 * and run() will execute the Thread.
 */
class TestThread implements Runnable{

	@Override
	public void run() {
		System.out.println("This will execcute the thread! ");
		for(int i=0;i<10;i++) {
			Thread th = Thread.currentThread();
			System.out.println(th.getName()+" "+(i+1));
			
			try {
				Thread.sleep(100);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}

public class SampleThreadImplementExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(new TestThread());
		t1.start();
		
		Thread t2 = new Thread(new TestThread());
		t2.start();
	}

}
