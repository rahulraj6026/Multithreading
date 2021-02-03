package com.threads.start;

public class SampleThreadImplementExample2 {

	public static void main(String[] args) {
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("This will execcute the thread! ");
				for (int i = 0; i < 10; i++) {
					Thread th = Thread.currentThread();
					System.out.println(th.getName() + " " + (i + 1));

					try {
						Thread.sleep(100);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});
		th.start();
	}

}
