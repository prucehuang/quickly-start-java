package com.git.huanghaifeng.java.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadDemo extends Thread {

	long minPrime;
	boolean running_flag = true;

	public boolean isRunning_flag() {
		return running_flag;
	}

	public void setRunning_flag(boolean running_flag) {
		this.running_flag = running_flag;
	}

	public void stopThread() throws InterruptedException {
		this.setRunning_flag(false);
		//wait for all son thread finish
		this.join();
	}
	
	ThreadDemo(long minPrime) {
		this.minPrime = minPrime;
	}				

	
	@Override
	public void run() {
		while (this.running_flag) {
			System.out.println(this.getName());
			try {
				sleep(6 * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(this.getName() + " finish");
	}

	public static void main(String[] args) {
		List<ThreadDemo> list = new ArrayList<ThreadDemo>();
		int thread_num = 3;
        for (int i = 0; i < thread_num; i++) {  
        	ThreadDemo demo = new ThreadDemo(0);  
            demo.start();  
            list.add(demo);  
        }

		try {
			for (ThreadDemo demo : list) {
				Thread.sleep(500);
				demo.stopThread();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (ThreadDemo demo : list) {
			System.out.println(demo.getState());
		}
		System.out.println("main thread finish");
	}
}
