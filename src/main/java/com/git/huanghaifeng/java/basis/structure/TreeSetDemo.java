package com.git.huanghaifeng.java.basis.structure;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetDemo {
	public static void main(String[] args) {
		TreeSet<String> parts = new TreeSet<>(new MyComparator());
		parts.add("Coaster");
		parts.add("Bidget");
		parts.add("Aodem");
		System.out.println(parts);
		System.out.println(parts.first());
		NavigableSet<String> sortByDescription = new TreeSet<>();

		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
}

class MyComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return -o1.compareTo(o2);
	}
}
