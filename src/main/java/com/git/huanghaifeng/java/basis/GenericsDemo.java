package com.git.huanghaifeng.java.basis;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛化
 * 泛化方法 —— 参数的类型不受限制
 * 
 * @author huanghaifeng
 * @version 1.0
 */
public class GenericsDemo {

	public static void main(String[] args) {
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();
		name.add("icon");
		age.add(18);
		number.add(314);
		
		getData(name);
		getData(age);
		getData(number);

		//getUperNumber(name);//1
		getUperNumber(age);//2
		getUperNumber(number);//3

		// 创建不同类型数组： Integer, Double 和 Character
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

		System.out.println("整型数组元素为:");
		printArray(intArray); // 传递一个整型数组
		System.out.println("\n双精度型数组元素为:");
		printArray(doubleArray); // 传递一个双精度型数组
		System.out.println("\n字符型数组元素为:");
		printArray(charArray); // 传递一个字符型数组

		System.out.printf("%d, %d 和 %d 中最大的数为 %d\n\n", 3, 4, 5, maximum(3, 4, 5));
		System.out.printf("%.1f, %.1f 和 %.1f 中最大的数为 %.1f\n\n", 6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));
		System.out.printf("%s, %s 和 %s 中最大的数为 %s\n", "pear", "apple", "orange", maximum("pear", "apple", "orange"));
	}

	public static void getData(List<?> data) {
		System.out.println("data :" + data.get(0));
	}

	public static void getUperNumber(List<? extends Number> data) {
		System.out.println("data :" + data.get(0));
	}

	public static <E> void printArray(E[] inputArray) {
		// 输出数组元素            
		for (E element : inputArray) {
			System.out.printf("%s ", element);
		}
		System.out.println();
	}

	// 比较三个值并返回最大值
	public static <T extends Comparable<T>> T maximum(T x, T y, T z) {
		T max = x; // 假设x是初始最大值
		if (y.compareTo(max) > 0) {
			max = y; //y 更大
		}
		if (z.compareTo(max) > 0) {
			max = z; // 现在 z 更大           
		}
		return max; // 返回最大对象
	}

}
