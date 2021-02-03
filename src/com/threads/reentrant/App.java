package com.threads.reentrant;

public class App {
	public static void main(String[] args) {
		SampleEntrant sample = new SampleEntrant();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					sample.firstThread();
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
					sample.secondThread();
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
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		sample.finished();
	}

}
