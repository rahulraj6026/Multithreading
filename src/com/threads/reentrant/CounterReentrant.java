package com.threads.reentrant;

public class CounterReentrant implements Runnable {
	private int id;
	private int count = 0;

	// A method to increment the count
	private void increment() {
		count++;
	}

	public CounterReentrant(int id) {
		this.id = id;
	}

	public void run() {
//		System.out.println("Thread id: " + id);
		increment();
		System.out.print("Thread id: "+ id);
		System.out.println(" Count: "+ count);
	}
}
