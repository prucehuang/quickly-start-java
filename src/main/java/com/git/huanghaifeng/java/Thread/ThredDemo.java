package com.git.huanghaifeng.java.Thread;

public class ThredDemo extends Thread {
	long minPrime;

	ThredDemo(long minPrime) {
		this.minPrime = minPrime;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this.getName());
			try {
				sleep(10*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		ThredDemo demo = new ThredDemo(101);
		demo.setPriority(NORM_PRIORITY);
		demo.start();
		ThredDemo demo1 = new ThredDemo(101);
		demo1.setPriority(MAX_PRIORITY);
		demo1.start();
		ThredDemo demo2 = new ThredDemo(101);
		demo2.setPriority(MIN_PRIORITY);
		demo2.start();

	}

}
