package com.git.huanghaifeng.java.basis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StreamDemo {

	public static void main(String[] args) throws IOException {
		
		File test_file = new File("/tmp/test.txt");
		FileOutputStream file_output = new FileOutputStream(test_file);
		// 构建FileOutputStream对象,文件不存在会自动新建
		OutputStreamWriter writer = new OutputStreamWriter(file_output, "UTF-8");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		System.out.println("Enter lines of text.");
		System.out.println("Enter 'end' to quit.");
		do {
			str = br.readLine();
			System.out.println(str);
			writer.append(str);
			writer.append("\n");
		} while (!str.equals("end"));

		writer.close();
		file_output.close();

		FileInputStream file_input = new FileInputStream(test_file);
		InputStreamReader reader = new InputStreamReader(file_input, "UTF-8");
		StringBuffer sb = new StringBuffer();
		while (reader.ready()) {
			sb.append((char) reader.read());
		}
		System.out.println(sb.toString());
		
		reader.close();
		file_input.close();
	}
}
