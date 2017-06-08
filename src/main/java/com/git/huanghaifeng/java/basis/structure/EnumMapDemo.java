package com.git.huanghaifeng.java.basis.structure;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapDemo {

	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> enumMap = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
		enumMap.put(AlarmPoints.KITCHEN, new Command() {
			@Override
			public void action() {
				System.out.println("action:kitchen");
			}
		});
		enumMap.put(AlarmPoints.BATHROOM, new Command() {
			@Override
			public void action() {
				System.out.println("action:bathroom");
			}
		});

		for (Map.Entry<AlarmPoints, Command> entry : enumMap.entrySet()) {
			System.out.println(entry.getKey());
			entry.getValue().action();
		}

		System.out.println(enumMap.get(AlarmPoints.UTILITY) + "");

	}
}

enum AlarmPoints {
	KITCHEN, BATHROOM, UTILITY
}

interface Command {
	void action();
}