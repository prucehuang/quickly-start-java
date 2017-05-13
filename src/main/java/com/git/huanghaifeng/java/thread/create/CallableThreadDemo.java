package com.git.huanghaifeng.java.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadDemo implements Callable<String> {
	
	public static void main(String[] args) {
		CallableThreadDemo callable_thread = new CallableThreadDemo();
		
		FutureTask<String> faeature = new FutureTask<String>(callable_thread);
		
		new Thread(faeature).start();
		
		System.out.println("这是主线程；begin！");
		//注意细细体会这个，只有主线程get了，主线程才会继续往下面执行
		try {
			System.out.println("得到的返回结果是："+faeature.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("这是主线程；end！");
	}

	@Override
	public String call() throws Exception {
		try {
			System.out.println(Thread.currentThread().getName() + "is calling");
			//模拟做事情执行了500毫秒；
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "son thread name is " + Thread.currentThread().getName();
	}
}