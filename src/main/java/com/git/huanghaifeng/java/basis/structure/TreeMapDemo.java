package com.git.huanghaifeng.java.basis.structure;

import java.util.NavigableMap;
import java.util.TreeMap;

public class TreeMapDemo {
	public static void main(String[] args) {
		TreeMap<String, Integer> parts = new TreeMap<>();
		parts.put("Coaster", 23);
		parts.put("Bidget", 24);
		parts.put("Aodem", 22);
		System.out.println(parts);
		System.out.println(parts.firstKey());
		System.out.println(parts.ceilingEntry("Bidget"));
		NavigableMap<String, Integer> sortByDescription = new TreeMap<>();
		
		sortByDescription.putAll(parts);
		System.out.println(sortByDescription);
	}
}
