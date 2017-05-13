package com.git.huanghaifeng.java.basis.structure;

import java.util.Enumeration;
import java.util.Vector;

public class VectorDemo {
	public static void main(String args[]) {
		// initial size is 3, increment is 2
		Vector<Object> v = new Vector<Object>(3, 2);
		System.out.println("Initial size: " + v.size());
		System.out.println("Initial capacity: " + v.capacity());
		v.addElement(new Integer(1));
		v.addElement(new Integer(2));
		v.addElement(new Integer(3));
		v.addElement(new Integer(4));
		System.out.println("Capacity after four additions: " + v.capacity());

		v.addElement(new Double(5.45));
		System.out.println("Current capacity: " + v.capacity());
		v.addElement(new Double(6.08));
		System.out.println("Current capacity: " + v.capacity());
		v.addElement(new Float(9.4));
		System.out.println("Current capacity: " + v.capacity());

		System.out.println("First element: " + v.firstElement());
		System.out.println("Last element: " + v.lastElement());

		if (v.contains(new Integer(3))) {
			System.out.println("Vector contains 3");
		}

		// enumerate the elements in the vector.
		Enumeration<Object> vEnum = v.elements();
		System.out.println("\nElements in vector:");
		while (vEnum.hasMoreElements()) {
			System.out.print(vEnum.nextElement() + " ");
		}
		System.out.println();
	}
}
