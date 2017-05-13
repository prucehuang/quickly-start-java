package com.git.huanghaifeng.java.basis.structure;

import java.util.Enumeration;
import java.util.Vector;

public class EnumerationDemo {
	public static void main(String args[]) {
		Vector<String> days_vector = new Vector<String>();
		days_vector.add("Sunday");
		days_vector.add("Monday");
		days_vector.add("Tuesday");
		days_vector.add("Wednesday");
		days_vector.add("Thursday");
		days_vector.add("Friday");
		days_vector.add("Saturday");

		Enumeration<String> days_enumeration = days_vector.elements();
		while (days_enumeration.hasMoreElements()) {
			System.out.println(days_enumeration.nextElement());
		}
	}
}
