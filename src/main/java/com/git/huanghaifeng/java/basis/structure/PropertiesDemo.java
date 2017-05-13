package com.git.huanghaifeng.java.basis.structure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
	public static void main(String args[]) {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("conf/PropertiesDemo.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(properties.getProperty("a", "none"));
		System.out.println(properties.getProperty("age", "none"));

		properties.put("Illinois", "Springfield");
		properties.put("Missouri", "Jefferson City");

		// Show all states and properties in hashtable.
		Set<Object> states = properties.keySet(); // get set-view of keys
		Iterator<Object> itr = states.iterator();
		while (itr.hasNext()) {
			String str = (String) itr.next();
			System.out.println("The capital of " + str + " is " + properties.getProperty(str) + ".");
		}

	}
}
