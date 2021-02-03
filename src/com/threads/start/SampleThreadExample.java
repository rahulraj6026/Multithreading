package com.threads.start;

class Runner extends Thread{

	@Override
	public void run() {
		System.out.println("This will execcute the thread! ");
		for(int i=0;i<10;i++) {
			System.out.println(this+" "+(i+1));
			
			try {
				Thread.sleep(1000);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

public class SampleThreadExample {

	public static void main(String[] args) {
		Runner r = new Runner();
		r.start();
		
		Runner r1 = new Runner();
		r1.start();
	}

}
