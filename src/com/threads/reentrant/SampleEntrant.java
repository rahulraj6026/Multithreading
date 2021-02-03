package com.threads.reentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SampleEntrant {
	// create a variable to keep track of count
	private int count = 0;
	// we will make use of reentrant method to lock and unlock the object
	private Lock lock = new ReentrantLock();
	// create a new condition to specifiy wait and notify
	Condition cond = lock.newCondition();

	// A method to increment the count
	private void increment() {
		for (int i = 0; i < 100; i++)
			count++;
	}

	// method for first thread
	public void firstThread() throws InterruptedException {
		// lock the thread
		lock.lock();
		System.out.println("First Thread is locked... ");

		// call await to release the lock and wait until signal is called
		cond.await();
		System.out.println("First thread moved to waiting state...");

		// increment the count
		increment();
		// unlock the thread
		lock.unlock();
	}

	// method for second thread
	public void secondThread() throws InterruptedException {
		// lock the thread
		lock.lock();
		System.out.println("Second Thread is locked...");

		// process something say Enter key
		System.out.println("Press Enter key...");
		new Scanner(System.in).nextLine();
		System.out.println("Enter key is pressed...");

		// After the operation is completed we call signal which wakes up the waiting
		// thread. If unlock is not called then the waiting thread will not acquire the
		// lock and will be in waiting state
		cond.signal();

		// increment the count
		increment();
		// unlock the thread
		lock.unlock();
	}

	public void finished() {
		System.out.println("Count is: " + count);
	}
}
