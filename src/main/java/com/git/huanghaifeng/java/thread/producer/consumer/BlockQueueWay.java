package com.git.huanghaifeng.java.thread.producer.consumer;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueueWay {
	private static int MAXQUEUESIZE = 10;
	private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(MAXQUEUESIZE);

	public static void main(String[] args) {
		BlockQueueWay block_queue_way = new BlockQueueWay();
		Producer producer = block_queue_way.new Producer();
		Consumer consumer = block_queue_way.new Consumer();

		producer.start();
		consumer.start();

		try {
			Thread.sleep(500);
			// stop all thread
			producer.stopProducer();
			consumer.stopConsumer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("主进程结束退出");
	}

	class Consumer extends Thread {

		@Override
		public void run() {
			consume();
		}

		boolean running_flag = true;

		public boolean isRunning_flag() {
			return running_flag;
		}

		public void setRunning_flag(boolean running_flag) {
			this.running_flag = running_flag;
		}

		@SuppressWarnings("static-access")
		public void stopConsumer() throws InterruptedException {
			//wait all data consumer
			while(!queue.isEmpty()) {
				this.sleep(300);
				System.out.println("等待消费者消费完全部数据，队列中剩下" + queue.size() + "个元素");
			}
			this.setRunning_flag(false);
			//wait for all son thread finish
			this.join();
			System.out.println("消费者线程结束，队列中剩下" + queue.size() + "个元素");
		}

		public void stopConsumer(boolean is_consumer_all_data) throws InterruptedException {
			if(is_consumer_all_data) {
				this.stopConsumer();
			} else {
				this.setRunning_flag(false);
				//wait for all son thread finish
				this.join();
				System.out.println("消费者线程结束，队列中剩下" + queue.size() + "个元素");
			}
		}

		private void consume() {
			while (running_flag) {
				queue.poll();
				System.out.println("从队列取走一个元素，队列中剩下" + queue.size() + "个元素");

				try {
					sleep(50);
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
		boolean running_flag = true;

		public boolean isRunning_flag() {
			return running_flag;
		}

		public void setRunning_flag(boolean running_flag) {
			this.running_flag = running_flag;
		}

		public void stopProducer() throws InterruptedException {
			this.setRunning_flag(false);
			//wait for all son thread finish
			this.join();
			System.out.println("生产者线程结束，队列中剩下" + queue.size() + "个元素");
		}

		private void produce() {
			while (running_flag) {
				try {
					queue.put(1);
					System.out.println("向队列取中插入一个元素，队列中剩下：" + queue.size() + "个元素");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
