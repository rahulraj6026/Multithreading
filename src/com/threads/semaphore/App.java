package com.threads.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) throws InterruptedException {
		// Creates a thread pool that creates new threads as needed, but will reuse
		// previously constructed threads when they are available. These pools will
		// typically improve the performance of programs that execute many short-lived
		// asynchronous tasks.Calls to execute will reuse previously constructed threads
		// if available. If no existing thread is available, a new thread will be
		// created
		// and added to the pool. Threads that have not been used for sixty seconds are
		// terminated and removed from the cache.
		ExecutorService execute = Executors.newCachedThreadPool();

		// create 200 threads
		for (int i = 0; i < 200; i++) {
			execute.submit(new Runnable() {

				@Override
				public void run() {
					Connection.getInstance().connect();
				}
			});
		}

		// Initiates an orderly shutdown in which previously submitted tasks are
		// executed, but no new tasks will be accepted.Invocation has no additional
		// effect if already shut down.
		execute.shutdown();
		
		execute.awaitTermination(1, TimeUnit.DAYS);
	}
}
