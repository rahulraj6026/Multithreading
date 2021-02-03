package com.threads.synchronization;

import java.util.Scanner;

/*
 * This example is for synchronization using volatile keyword
 * This keyword is helpful to synchronize the value of only one variable between thread and main memory
 * In this example we will just track the boolean value status to start or stop the process. This can be done by enter click through which we will get notified that the process has come to the end
 */
class Processor extends Thread{
	volatile boolean status = true;
	
	public void run() {
		while(status) {
			System.out.println("testing");
		}
	}
	
	public void shutdown() {
		status = false;
	}
}
public class ThreadVolatile {

	public static void main(String[] args) {
		Processor p1 = new Processor();
		p1.start();
		
		Processor p2 = new Processor();
		p2.start();
		
		//track the enter button
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		
		p1.shutdown();
		p2.shutdown();
		
		sc.close();
	}

}
