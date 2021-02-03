package com.threads.interrupt;

import java.util.Random;

public class InterruptExample {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				Random random = new Random();

				for (int i = 0; i < 1E7; i++) {
					// one way to check if thread is interrupted
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						System.out.println("Interrupted!!");
//						break;
//					}

					// another way to check if thread is interrupted
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted!!");
						break;
					}
					
					Math.asin(random.nextDouble());
				}
			}
		});

		System.out.println("Started the thread... ");
		t1.start();

		System.out.println("Into sleep mode... ");
		Thread.sleep(500);

		// interrupt thread
		t1.interrupt();

		t1.join();

		System.out.println("Finished... ");
	}

}
