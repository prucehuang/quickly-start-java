package com.git.huanghaifeng.java.thread.create;

public class RunnableDemo implements Runnable {
	long minPrime;

	RunnableDemo(long minPrime) {
		this.minPrime = minPrime;
	}

	public void run() {
		while (true) {
			System.out.println(this.minPrime);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		RunnableDemo p = new RunnableDemo(143);
		new Thread(p).start();
	}
}