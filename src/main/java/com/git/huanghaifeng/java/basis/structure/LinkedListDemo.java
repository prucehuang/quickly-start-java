package com.git.huanghaifeng.java.basis.structure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListDemo {
	public static void main(String[] args) {
		List<String> a = new LinkedList<>();
		a.add("Amy_a");
		a.add("Carl_a");
		a.add("Erica_a");

		List<String> b = new LinkedList<>();
		b.add("Bob_b");
		b.add("Doug_b");
		b.add("Alter_b");

		// merge the words from b into a
		ListIterator<String> a_iter = a.listIterator();
		Iterator<String> b_iter = b.iterator();
		while (b_iter.hasNext()) {
			if (a_iter.hasNext())
				a_iter.next();
			a_iter.add(b_iter.next());
		}
		System.out.println(a);

		// remove every second word from b
		b_iter = b.iterator();
		while (b_iter.hasNext()) {
			b_iter.next(); // skip one element
			if (b_iter.hasNext()) {
				b_iter.next(); // skip next element
				b_iter.remove(); // remove that element
			}
		}
		System.out.println(b);

		// bulk operation: remove all words in b from a
		a.removeAll(b);
		System.out.println(a);
	}

}
