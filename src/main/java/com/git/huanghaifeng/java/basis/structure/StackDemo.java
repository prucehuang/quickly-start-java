package com.git.huanghaifeng.java.basis.structure;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackDemo {

	public static void main(String args[]) {
		Stack<Integer> stack = new Stack<Integer>();
		// push
		System.out.println("Stack<Integer>: " + stack);
		stack.push(42);
		System.out.println("Stack<Integer>: " + stack);
		stack.push(66);
		System.out.println("Stack<Integer>: " + stack);
		stack.push(99);
		System.out.println("Stack<Integer>: " + stack);

		// pop
		try {
			System.out.println("pop -> " + stack.pop());
			System.out.println("Stack<Integer>: " + stack);
			System.out.println("pop -> " + stack.pop());
			System.out.println("Stack<Integer>: " + stack);
			System.out.println("pop -> " + stack.pop());
			System.out.println("Stack<Integer>: " + stack);
			System.out.println("pop -> " + stack.pop());
		} catch (EmptyStackException e) {
			System.out.println("empty Stack<Integer>");
		}
	}
}
