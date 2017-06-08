package com.git.huanghaifeng.java.basis.structure;

import java.util.EnumSet;

public class EnumSetDemo {
	enum Weekdays {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
	}

	public static void main(String[] args) {
		EnumSet<Weekdays> set = null; // LINE A
		System.out.println(set);

		set = EnumSet.of(Weekdays.Monday); // LINE B
		System.out.println("set when added monday : " + set);

		set = EnumSet.of(Weekdays.Tuesday); // LINE C
		System.out.println("set when added tuesday : " + set);

		EnumSet<Weekdays> weekends = EnumSet.of(Weekdays.Saturday, Weekdays.Sunday); // LINE D
		System.out.println("weekends : " + weekends);

		EnumSet<Weekdays> otherweekdays = EnumSet.complementOf(weekends); // LINE E
		System.out.println("Otherdays : " + otherweekdays);

		set = EnumSet.allOf(Weekdays.class); // LINE F
		EnumSet<Weekdays> setCopy = EnumSet.copyOf(set); // LINE G
		System.out.println("setCopy elements : " + setCopy);
	}
}
