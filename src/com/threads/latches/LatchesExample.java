package com.threads.latches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * This is a sample program for latches. Its used  when a thread needs to wait for other threads before starting its work. 
 */
class Processor implements Runnable {
	// create a temporary variable for latches
	CountDownLatch latches;

	// create a constructor and assign the result
	public Processor(CountDownLatch latches) {
		this.latches = latches;
	}

	// implement the run method
	public void run() {
		System.out.println("The process is started....");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// decrease the latch count as processing is done
		latches.countDown();
		System.out.println("Finished Process: " + Thread.currentThread().getName());
	}
}

public class LatchesExample {

	public static void main(String[] args) {
		// create count down latches
		// count the number of times countDown must be invoked before threads can pass
		// through await
		CountDownLatch latches = new CountDownLatch(3);

		// create threads with latches say 3 in this case.
		ExecutorService executors = Executors.newFixedThreadPool(3);
		
		//created threads and start the execution by passing on the latches.
		for(int i=0; i<3; i++) {
			executors.submit(new Processor(latches));
		}
		
		//Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted. 
		try {
			latches.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " has finished"); 
	}

}
