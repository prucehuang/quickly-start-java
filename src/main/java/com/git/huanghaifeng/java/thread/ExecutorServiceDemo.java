package com.git.huanghaifeng.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		// 创建一个线程池
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(){
				@Override
				public void run(){
					System.out.println(this.getName());				
					try {
						double d = Math.random();
						int i = (int)(d*100);
						Thread.sleep(100000-i);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			};
			executor.execute(thread);
		}
		executor.shutdown();

		try {
			// awaitTermination返回false, 即超时,子线程还在运行
			// awaitTermination返回true, 主线程退出
			// 每隔10秒循环一次
			while (!executor.awaitTermination(10, TimeUnit.SECONDS)){
				System.out.println("wait for son thread finish");
			};
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("子线程执行时长：" + (end - start));
	}
}
