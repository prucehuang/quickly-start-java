package com.git.huanghaifeng.java.basis.structure;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<LocalDate> pq = new PriorityQueue<>();
		pq.offer(LocalDate.of(1906, 12, 9)); // G. Hopper
		pq.offer(LocalDate.of(1815, 12, 10)); // A. Lovelace
		pq.offer(LocalDate.of(1903, 12, 3)); // J. von Neumann
		pq.offer(LocalDate.of(1910, 6, 22)); // K. Zuse

		System.out.println("建立极小堆...");
		for (LocalDate date : pq)
			System.out.println(date);
		System.out.println("Removing elements...");
		while (!pq.isEmpty())
			System.out.println(pq.poll());

		Comparator<Integer> cmp = new Comparator<Integer>() {
			public int compare(Integer e1, Integer e2) {
				return e2 - e1;
			}
		};
		PriorityQueue<Integer> priority_queue = new PriorityQueue<>(cmp);
		priority_queue.offer(8);
		priority_queue.offer(3);
		priority_queue.offer(10);
		priority_queue.offer(12);
		priority_queue.offer(2);
		while (!priority_queue.isEmpty())
			System.out.println(priority_queue.poll());		
	}

}
