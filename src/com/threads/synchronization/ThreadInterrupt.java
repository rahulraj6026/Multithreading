package com.threads.synchronization;

class myThread implements Runnable{
	//for creating the Thread
	Thread t;

	//constructor
	public myThread() {
		//create the thread
		t = new Thread(this);
		System.out.println("Thread is created...");
		t.start();
	}

	@Override
	public void run() {
		System.out.println("Thread is started...");
		while(!Thread.interrupted()) {
			System.out.println("Still running...");
		}
		System.out.println();
	}
}
public class ThreadInterrupt {
	public static void main(String[] args) {
		//calling the constructor
		myThread t1 = new myThread();
		
		try {
			Thread.sleep(100);
			
			//interrupt the created thread
			t1.t.interrupt();
			
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting the thread...");
	}

}
