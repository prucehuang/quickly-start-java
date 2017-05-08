package com.git.huanghaifeng.java.basis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		String input = "I missing " + "someone.";
		String regex = ".*I.*";

		boolean isMatch = Pattern.matches(regex, input);
		System.out.println("方法一。字符串中是否包含了 'I' 子字符串? " + isMatch);

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		System.out.println("方法二，字符串中是否包含了 'I' 子字符串? " + matcher.matches());

		// 按指定模式在字符串查找
		String line = "This order was placed for QT3000! OK?";
		String regex_group = "(\\D*)(\\d+)(.*)";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(regex_group);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found value: " + m.group(0));
			System.out.println("Found value: " + m.group(1));
			System.out.println("Found value: " + m.group(2));
			System.out.println("Found value: " + m.group(3));
		} else {
			System.out.println("NO MATCH");
		}
	}

}
