package com.threads.producerconsumer;

import java.util.LinkedList;
import java.util.Random;

class SyncProcessor {
	// LL to add Integers
	LinkedList<Integer> list = new LinkedList<Integer>();
	// variable to store limit size
	Integer size = 10;
	// An object to handle the lock
	Object lock = new Object();

	void produce() throws InterruptedException {
		int value = 0;
		while (true) {
			synchronized (lock) {
				// check if size is reached to limit
				while (list.size() == size) {
					// move into waiting state until item is consumed
					lock.wait();
					System.out.println("Producer into waiting state... ");
				}

				// add the value to the list
				list.add(value++);
				System.out.println("List size after producing.. " + list.size());
				// notify the consumer object to continue its execution
				lock.notify();
				System.out.println("Consumer can continue it's execution... ");
				System.out.println();
			}
		}
	}

	void consume() throws InterruptedException {
		while (true) {
			Random random = new Random();
			synchronized (lock) {
				while (list.size() == 0) {
					// lock until there is an item to consume in the storage
					lock.wait();
					System.out.println("Consumer into waiting state... ");
				}

				System.out.print("List size is: " + list.size());
				int val = list.removeFirst();
				System.out.println(" Value Removed is: " + val);
				// make the producer to execute as it is in waiting state
				lock.notify();
				System.out.println("Producer can continue its execution... ");
				System.out.println();
			}
			Thread.sleep(random.nextInt(1000));
		}
	}
}

class SampleTest extends Thread {
	SyncProcessor p = new SyncProcessor();

	Thread t1 = new Thread(new Runnable() {
		public void run() {
			try {
				p.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

	Thread t2 = new Thread(new Runnable() {
		public void run() {
			try {
				p.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
}

public class SyncProducerConsumer {
	public static void main(String[] args) throws InterruptedException {
		SampleTest sample = new SampleTest();
		sample.t1.start();
		sample.t2.start();

		sample.t1.join();
		sample.t2.join();
	}
}
