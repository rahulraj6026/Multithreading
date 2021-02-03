package com.threads.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 *There are two solutions for this. 
 *
 *One is we need to lock in the same sequence to avoid deadlock. 
 *acquire lock1 and then go to sleep for second thread to acquire the lock.
 *lock1 is already acquired and lock2 is acquire by second thread. 
 *When thread1 tries to acquire lock2 then it is already acquired. 
 *same is the case with lock1 and we enter deadlock state.
 *
 *Another solution:
 *
 *We will create a method which check whether lock1 and lock2 both are acquired. If it is so then we further process. 
 *Else we check whether any thread is locked and then unlock it and and loop until we get both the threads locked.
 *Once both the threads are acquired we return back to execution
 */

class Sample {
	// create account class
	Account acc1 = new Account();
	Account acc2 = new Account();

	// create two objects for locking mechanism
	public Lock lock1 = new ReentrantLock();
	public Lock lock2 = new ReentrantLock();

	public void balance() {
		System.out.println("Account one " + acc1.getBalance());
		System.out.println("Account two " + acc2.getBalance());
		System.out.println("Total " + (acc1.getBalance() + acc2.getBalance()));
	}

	public void acquireLock(Lock lock1, Lock lock2) {
		boolean status1 = false;
		boolean status2 = false;

		// we will loop until both the locks are acquired. If any of the lock is locked
		// then we unlock it so as it is required to acquire both the locks
		while (true) {
			try {
				status1 = lock1.tryLock();
				status2 = lock2.tryLock();
			} finally {
				if (status1 && status2) {
					System.out.println("Both locks acquired... ");
					return;
				}
				if (status1)
					lock1.unlock();
				if (status2)
					lock2.unlock();
			}
		}
	}

	public void ThreadOne() {
		// lock both the threads
		System.out.println("Get both the locks for Thread 1 ");
//		lock1.lock();
//		lock2.lock();
		acquireLock(lock1, lock2);

		try {
			acc1.deposit(100);
		} finally {
			System.out.println("Unlock both the threads...");
			lock1.unlock();
			lock2.unlock();
			acc1.transfer(acc1, acc2, 50);
		}

	}

	public void ThreadTwo() {
		// lock both the threads
		System.out.println("Get both the locks for Thread 2 ");
//		lock2.lock();
//		lock1.lock();
		acquireLock(lock1, lock2);

		try {
			acc2.deposit(200);
		} finally {
			System.out.println("Unlock both the threads...");
			lock1.unlock();
			lock2.unlock();
			acc2.transfer(acc2, acc1, 50);
		}
	}
}

public class SyncApp {
	public static void main(String[] args) throws InterruptedException {
		Sample s = new Sample();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				s.ThreadOne();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				s.ThreadTwo();
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		s.balance();
	}

}
