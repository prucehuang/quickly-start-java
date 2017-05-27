package com.git.huanghaifeng.java.basis.structure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HashSetDemo {

	public static void main(String[] args) {
		Set<String> words = new HashSet<>(); // HashSet implements Set

		try (Scanner in = new Scanner(System.in)) {
			while (words.size()<3) {
				System.out.println(words.add(in.next()));
			}
		}

		System.out.println(". . .");
		Iterator<String> iter = words.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		System.out.println(words.size() + " distinct words. ");
	}
}
