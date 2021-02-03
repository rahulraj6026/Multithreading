package com.threads.synchronization;

public class TestClass {
	private static class Demo implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				System.out.println(i);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Thread d = new Thread(new Demo());
		d.start();
	}
}
