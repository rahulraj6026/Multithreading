package com.threads.synchronization;

/*
 * This is a method implementation for synchronized keyword. Here we increment the count of the variable count with two separate threads.
 * The issue is when we run this without join() then it will be zero as the threads are not executed and we are printing the result.
 * To fix the above issue we call the join() method which will wait until the joined thread is dead.
 * 
 * After this fix we run and check the count variable and observe that the value is fluctuating and it is not 2000 always. This is because if thread1 is executing and then thread2 is also executing and at this time the value is 100 then both will assign a value of 101.
 * 
 * To fix this issue we need to make the method increment synchronized so as to update the count variable correctly
 * We can make use of volatile but it will not fix the issue as it is not caused because of caching. This is because of Thread interleaving.
 * 
 * By making the method synchronized we lock the method and let first thread complete the execution followed by the second thread.
 */
public class ThreadSynchronization {
	public static void main(String[] args) {
		App app = new App();
		app.performTask();
	}
}

class App {
	private int count = 0;
	public synchronized void increment(){count++;}
	
	public void performTask() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<1000; i++) {
					increment();
				}
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<1000; i++) {
					increment();
				}
			}
		});
		
		System.out.println("The execution of the thread has been started ");
		
		t1.start();
		t2.start();
		
		//This will run step by step hence we will join these two threads with the main thread
		try {
			t1.join();
			t2.join();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Count Variable is : "+ count);
	}
}
