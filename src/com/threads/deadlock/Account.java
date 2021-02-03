package com.threads.deadlock;

public class Account {
	private int amount = 0;

	public void deposit(int a) {
		amount += a;
	}

	public void withdraw(int a) {
		amount -= a;
	}

	public int getBalance() {
		return amount;
	}

	public void transfer(Account acc1, Account acc2, int amo) {
		acc1.deposit(amo);
		acc2.withdraw(amo);
	}
}
