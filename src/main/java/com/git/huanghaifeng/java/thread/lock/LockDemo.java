package com.git.huanghaifeng.java.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	public static void main(String[] args) {
		final Data data = new Data();
		new Thread() {
			public void run() {
				data.output("zhangsan");
			};
		}.start();
		new Thread() {
			public void run() {
				data.output("lisi");
			};
		}.start();
	}

	private static class Data {
		private Lock lock = new ReentrantLock();

		public void output(String name) {
			lock.lock();// 得到锁
			try {
				for (int i = 0; i < name.length(); i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();// 释放锁
			}
		}
	}

}