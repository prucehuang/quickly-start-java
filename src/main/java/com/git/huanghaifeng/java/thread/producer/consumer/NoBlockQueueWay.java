package com.git.huanghaifeng.java.thread.producer.consumer;

import java.util.PriorityQueue;

public class NoBlockQueueWay {

	private int queueSize = 10;
	private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

	public static void main(String[] args) {
		NoBlockQueueWay no_block_way = new NoBlockQueueWay();
		Producer producer = no_block_way.new Producer();
		Consumer consumer = no_block_way.new Consumer();

		producer.start();
		consumer.start();
	}

	class Consumer extends Thread {

		@Override
		public void run() {
			consume();
		}

		private void consume() {
			while (true) {
				synchronized (queue) {
					while (queue.size() == 0) {
						try {
							System.out.println("队列空，等待数据");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.poll(); // 每次移走队首元素
					queue.notify();
					System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
				}
				
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Producer extends Thread {

		@Override
		public void run() {
			produce();
		}

		private void produce() {
			while (true) {
				synchronized (queue) {
					while (queue.size() == queueSize) {
						try {
							System.out.println("队列满，等待有空余空间");
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
							queue.notify();
						}
					}
					queue.offer(1); // 每次插入一个元素
					queue.notify();
					System.out.println("向队列取中插入一个元素，队列剩余空间：" + queue.size());
				}
				
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}