package com.git.huanghaifeng.java.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	public static void main(String[] args) {
		final Data data = new Data();
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 5; j++) {
						data.set(new Random().nextInt(30));
					}
				}
			}).start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 5; j++) {
						data.get();
					}
				}
			}).start();
		}
	}

	private static class Data {
		private int data;// 共享数据
		private ReadWriteLock read_write_lock = new ReentrantReadWriteLock();

		public void set(int data) {
			read_write_lock.writeLock().lock();// 取到写锁
			try {
				System.out.println(Thread.currentThread().getName() + "准备写入数据");
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.data = data;
				System.out.println(Thread.currentThread().getName() + "写入" + this.data);
			} finally {
				read_write_lock.writeLock().unlock();// 释放写锁
			}
		}

		public void get() {
			read_write_lock.readLock().lock();// 取到读锁
			try {
				System.out.println(Thread.currentThread().getName() + "准备读取数据");
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "读取" + this.data);
			} finally {
				read_write_lock.readLock().unlock();// 释放读锁
			}
		}
	}
}
