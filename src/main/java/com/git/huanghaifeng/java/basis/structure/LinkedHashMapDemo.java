package com.git.huanghaifeng.java.basis.structure;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapDemo {
	public static void main(String[] args) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("apple", "苹果");
		map.put("watermelon", "西瓜");
		map.put("banana", "香蕉");
		map.put("peach", "桃子");

		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
}
