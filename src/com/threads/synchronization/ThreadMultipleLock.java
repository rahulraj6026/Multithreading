package com.threads.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * In this example we see how to add multiple locks using the synchronized code blocks.
 * To achieve this we will create two lists and add random integer values in a given range. When we try to run the threads we see that the size of both the lists are not same the loop runs for 1000 times.
 * To fix this we make the methods of the respective threads synchronized which will fix the issue but will execute slow as the thread acquires the lock of the class and releases once it is done with its execution.
 * To fix the above issue we create separate synchronized blocks in both the method implementation. By doing so it will reduce the processing time as if one block acquires lock for execution then other thread has to wait for other thread to complete its execution. After the execution the first block releases the lock then the second block executes the code. By doing this no threads can run the blocks at the same time which will increase the execution time.
 * We make us of lock variable in order to lock both the synchronized block separately
 */

public class ThreadMultipleLock {
	//create objects for synchronized blocks
	private Object lock1 = new Object();
	private Object lock2 = new Object();
			
	// create two lists to store the variables
	List<Integer> list1 = new ArrayList<>();
	List<Integer> list2 = new ArrayList<>();

	// create a object to fetch random variables
	public Random random = new Random();

	// execute thread one
	public void executeThreadOne() {
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int someNumber = random.nextInt(100);
			System.out.println("Thread Name: " + Thread.currentThread().getName()+" Number added: "+someNumber);
			list1.add(someNumber);
		}
	}

	// execute thread two
	public void executeThreadTwo() {
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int someNumber = random.nextInt(100);
			System.out.println("Thread Name: " + Thread.currentThread().getName()+" Number added: "+someNumber);
			list2.add(someNumber);
		}
	}

	// Implementation of Threads
	public void startProcess() {
		for (int i = 0; i < 1000; i++) {
			executeThreadOne();
			executeThreadTwo();
		}
	}

	public void main() {

		//Calculate start time
		long start = System.currentTimeMillis();
		
		// create a method to run first thread
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				startProcess();
			}
		});

		// create a method to run second thread
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				startProcess();
			}
		});

		// start both the threads
		t1.start();
		t2.start();

		// to make this execute we need to join this to main thread. This will wait
		// until the thread dies
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//calculate the end time
		long end = System.currentTimeMillis();

		System.out.println("Time " + (end - start));
		System.out.println(list1.size() + " " + list2.size());
	}

}
