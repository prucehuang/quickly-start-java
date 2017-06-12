package com.git.huanghaifeng.java.thread.current;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 * @author huanghaifeng 
 * 
 */
public class ArrayBlockingQueueDemo extends Thread{

	private ArrayBlockingQueue<Integer> blocking_queue = new ArrayBlockingQueue<Integer>(1);

	public void put(int data) {
		try {
			blocking_queue.put(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int poll() {
		return blocking_queue.poll();
	}
	
	
	public static void main(String[] args) {

		new ArrayBlockingQueueDemo() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("------1------");
				}
			}
		}.start();

		new ArrayBlockingQueueDemo() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("------2------");
				}
			}
		}.start();

	}

}