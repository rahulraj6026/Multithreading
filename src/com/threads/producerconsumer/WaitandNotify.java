package com.threads.producerconsumer;

import java.util.Scanner;

class Processor {
	public void producer() throws InterruptedException {
		// acquire lock
		synchronized (this) {
			System.out.println("Producing a item... ");
			System.out.println("Entering waiting state... ");
			wait();// move into waiting state
			System.out.println("Producer is resumed... ");
		}
	}

	public void consumer() throws InterruptedException {
		synchronized (this) {
			Thread.sleep(10);
			Scanner sc = new Scanner(System.in);
			sc.nextLine();// execute until enter is clicked
			System.out.println("Return key is pressed... ");
			notify();// notify the waiting to resume the execution
			sc.close();
		}
	}

}

class Test extends Thread {
	Processor process = new Processor();

	@Override
	public void run() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					process.producer();
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
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					process.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class WaitandNotify {

	public static void main(String[] args) {
		Test t = new Test();
		t.start();
	}

}
