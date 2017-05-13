package com.git.huanghaifeng.java.basis;

import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("come on, write some double numble");

		double sum = 0;
		int m = 0;

		while (scanner.hasNextDouble()) {
			double x = scanner.nextDouble();
			m = m + 1;
			sum = sum + x;
		}

		System.out.println(m + "个数的和为" + sum);
		System.out.println(m + "个数的平均值是" + (sum / m));

	}

}
