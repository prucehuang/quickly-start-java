package com.git.huanghaifeng.java.basis;

/**
 * 泛化类基本使用 
 * @author huanghaifeng
 * 
 * @param <T> 参数类型、个数
 */
public class GenericsClassDemo<T> {
	private T t;

	public void add(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public static void main(String[] args) {
		GenericsClassDemo<Integer> integerBox = new GenericsClassDemo<Integer>();
		GenericsClassDemo<String> stringBox = new GenericsClassDemo<String>();

		integerBox.add(new Integer(10));
		stringBox.add(new String("hah"));

		System.out.printf("整型值为 :%d\n\n", integerBox.get());
		System.out.printf("字符串为 :%s\n", stringBox.get());
	}
}
