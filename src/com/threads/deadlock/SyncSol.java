package com.threads.deadlock;

public class SyncSol {
	// create objects to hold the lock
	public static Object Lock1 = new Object();
	public static Object Lock2 = new Object();
	// create two account objects
	static Account acc1 = new Account();
	static Account acc2 = new Account();

	// balance method to get the balance of particular accounts and total of both
	// the accounts
	public void balance() {
		System.out.println("Account one " + acc1.getBalance());
		System.out.println("Account two " + acc2.getBalance());
		System.out.println("Total " + (acc1.getBalance() + acc2.getBalance()));
	}

	public static void main(String args[]) throws InterruptedException {
		ThreadDemo1 T1 = new ThreadDemo1();
		ThreadDemo2 T2 = new ThreadDemo2();
		T1.start();
		T2.start();

		T1.join();
		T2.join();
		SyncSol sol = new SyncSol();
		sol.balance();

	}

	// acquire lock1 and then go to sleep for second thread to acquire the lock. Now
	// lock1 is already acquired and lock2 is acquire by second thread. When thread1
	// tries to acquire lock2 then it is already acquired and same is the case with
	// lock1 and we enter deadlock state
	private static class ThreadDemo1 extends Thread {
		public void run() {
			synchronized (Lock1) {
				System.out.println("Thread 1: Holding lock 1... ");
				acc1.deposit(100);
				System.out.println(acc1.getBalance() + " " + acc2.getBalance());

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				System.out.println("Thread 1: Waiting for lock 2...");

				synchronized (Lock2) {
					acc2.deposit(100);
					System.out.println("Thread 1: Holding lock 1 & 2... ");
					System.out.println(acc1.getBalance() + " " + acc2.getBalance());
				}
			}
		}
	}

	// for sequence of locks called Thread one: (lock1 and lock2) and Thread two:
	// (lock2 and lock1) we enter deadlock. To avoid this we follow Thread
	// one:(lock1 and lock2) Thread two:(lock1 and lock2)
	private static class ThreadDemo2 extends Thread {

		public void run() {
			synchronized (Lock1) {
				acc1.transfer(acc1, acc2, 50);
				System.out.println("Thread 2: Holding lock 1... ");
				System.out.println(acc1.getBalance() + " " + acc2.getBalance());

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
				System.out.println("Thread 2: Waiting for lock 2...");

				synchronized (Lock2) {
					acc2.transfer(acc2, acc1, 50);
					System.out.println("Thread 2: Holding lock 1 & 2... ");
					System.out.println(acc1.getBalance() + " " + acc2.getBalance());
				}
			}
		}
	}
}