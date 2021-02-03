package com.threads.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

	// create a ArrayBlockingQueue for this scenario
	private static BlockingQueue<Integer> block = new ArrayBlockingQueue<Integer>(10);

	public static void main(String[] args) {
		// create two threads
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// make a call to producer
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// start the threads
		t1.start();
		t2.start();

		// wait till all the thread execution is completed
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Current Thread " + Thread.currentThread().getName());

	}

	// create a method for producer
	private static void producer() throws InterruptedException {
		// generate random numbers
		Random random = new Random();

		// run a loop to just add a item into the queue
		while (true) {
			System.out.println("Item is produced successfully...");
			// produce a random number into the queue
			block.put(random.nextInt(100));

			System.out.println("Size of storage... " + block.size());
		}
	}

	// create a method for consumer
	private static void consumer() throws InterruptedException {
		// generate a random index
		Random random = new Random();

		while (true) {
			Thread.sleep(10);

			// check if index is zero if yes then consume the item
			if (random.nextInt(10) == 0) {
				// consume the item from the queue
				Integer item = block.take();

				System.out.println("Item is consumed...");
				System.out.println("Consumer item is: " + item);
				System.out.println("Size of storage is: " + block.size());
			}
		}
	}
}
