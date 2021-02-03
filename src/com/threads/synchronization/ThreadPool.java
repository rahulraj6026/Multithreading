package com.threads.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool implements Runnable {
	//create a id to track the thread with this id
	private int id;

	//update the thread value using this constructor
	public ThreadPool(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Process started..."+id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Process ended..." + id);
	}

	public static void main(String[] args) {
		// create a thread pool using executor service and using Executors
		// create number of threads by passing a value
		ExecutorService execute = Executors.newFixedThreadPool(2);
		//single thread execution
//		ExecutorService ex = Executors.newSingleThreadExecutor();
//		try {
//			execute.awaitTermination(1, TimeUnit.HOURS);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		for (int i = 0; i < 5; i++) {
			execute.submit(new ThreadPool(i+1));
		}

		// shutdown the pool once the execution is completed
		execute.shutdown();

		System.out.println("All tasks are started...");

		// This will block the executor until all the tasks are completed.
		try {
			execute.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All taks are completed...");
	}

}
