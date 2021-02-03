package com.threads.callablenfuture;

import java.io.IOError;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class TestRunnable implements Runnable {

	@Override
	public void run() {
		Random random = new Random();
		System.out.println("Starting... ");

		try {
			int duration = random.nextInt(4000);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Finished... ");
	}

}

public class SampleExample {

	public static void main(String[] args) throws InterruptedException {
		// create thread pool
		ExecutorService execute = Executors.newCachedThreadPool();

		// start executing the threads using runnable
		// execute.submit(new TestRunnable());

		// start executing the threads using callable
		// future will store the result returned which can't be done using Runnable and
		// run()
		Future<Integer> future = execute.submit(new Callable<Integer>() {

			// Future<?> future = execute.submit(new Callable<Void>() and add Void to call()
			// and return null for void type
			@Override
			public Integer call() throws Exception {
				System.out.println("starting... ");

				Random random = new Random();

				int duration = random.nextInt(4000);

				System.out.println("slept for: " + duration);

				// A test condition to return the result
				if (duration > 2000)
					throw new IOException("Very sleepy... ");

				Thread.sleep(duration);

				return duration;
			}

		});

		// stop as no threads are there for execution
		execute.shutdown();

		// wait for this much time until these threads are executed
		execute.awaitTermination(1, TimeUnit.HOURS);

		try {
			System.out.println("Duration is: " + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}
