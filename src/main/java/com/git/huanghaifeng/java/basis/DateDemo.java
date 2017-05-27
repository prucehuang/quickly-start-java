package com.git.huanghaifeng.java.basis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateDemo {

	public static void main(String[] args) {		
		System.out.println(System.currentTimeMillis());
		
		Date date = new Date();
		System.out.println(date.toString());

		SimpleDateFormat ft = new SimpleDateFormat("F E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		System.out.println(ft.format(date));
		
	    System.out.printf("全部日期和时间信息：%tc%n",date);          
	    System.out.printf("年-月-日格式：%tF%n",date);  
	    System.out.printf("月/日/年格式：%tD%n",date);  
	    System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);  
	    System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);  
	    System.out.printf("HH:MM格式（24时制）：%tR",date);  
	    
        //b的使用，月份简称  
        String str=String.format(Locale.US,"英文月份简称：%tb",date);       
        System.out.println(str);                                                                              
        System.out.printf("本地月份简称：%tb%n",date);  
        //B的使用，月份全称  
        str=String.format(Locale.US,"英文月份全称：%tB",date);  
        System.out.println(str);  
        System.out.printf("本地月份全称：%tB%n",date);  
        //a的使用，星期简称  
        str=String.format(Locale.US,"英文星期的简称：%ta",date);  
        System.out.println(str);  
        //A的使用，星期全称  
        System.out.printf("本地星期的简称：%tA%n",date);  
        //C的使用，年前两位  
        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",date);  
        //y的使用，年后两位  
        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",date);  
        //j的使用，一年的天数  
        System.out.printf("一年中的天数（即年的第几天）：%tj%n",date);  
        //m的使用，月份  
        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",date);  
        //d的使用，日（二位，不够补零）  
        System.out.printf("两位数字的日（不足两位前面补0）：%td%n",date);  
        //e的使用，日（一位不补零）  
        System.out.printf("月份的日（前面不补0）：%te%n%n",date); 

	}

}
