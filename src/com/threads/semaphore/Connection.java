package com.threads.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();
	
	// track the connections
	private int connections = 0;
	// setting up semaphore with ten resources with 10 permits
	Semaphore sem = new Semaphore(10);

	// method to return the instance
	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		// acquire the permit
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			doConnect();
		} finally {
			// release the permit as operations are done
			sem.release();
		}
	}

	// implement connect method which will acquire the permit and when the
	// operations is done we will call release which will allow other waiting
	// threads to access the permit
	public void doConnect() {
		// increase the connections as the thread is acquired
		synchronized (this) {
			connections++;
			System.out.print("Current connections: " + connections);
			System.out.println(" Connection acquired by thread: " + Thread.currentThread().getName());
		}

		// do some operations
		System.out.println("Some operation is performed... ");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (this) {
			connections--;
			System.out.print("Permit is released by Thread: " + Thread.currentThread().getName());
			System.out.println(" Total number of connection: " + connections);

		}
	}

}
