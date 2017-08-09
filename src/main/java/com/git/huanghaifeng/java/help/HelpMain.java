package com.git.huanghaifeng.java.help;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HelpMain {

	public static void main(String[] args) {
		//	     String input = "1 fish 2 fish red fish blue fish";
		//	     Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
		//	     System.out.println(s.nextInt());
		//	     System.out.println(s.nextInt());
		//	     System.out.println(s.next());
		//	     System.out.println(s.next());
		//	     s.close(); 

		//		Scanner scan = new Scanner(System.in); 
		//		
		//		System.out.println("随便来输点啥咯");
		//		while(!scan.hasNext("bye")){
		//			String str1 = scan.nextLine();
		//			System.out.println("输入的数据为："+str1);  
		//			System.out.println(Integer.MAX_VALUE);
		//			System.out.println(HelpMain.class.getName());
		//		}
		String log = "<  10289218> END  :L0301002:11-15:55:00-085160  cost [1]ms\n";
		System.out.println(log.split("cost \\[")[1].split("]")[0]);
	}
}
