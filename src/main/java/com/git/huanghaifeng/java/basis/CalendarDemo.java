package com.git.huanghaifeng.java.basis;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class CalendarDemo {

	public static void main(String[] args) {

		Calendar calendar_end = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();

		System.out.println(calendar.getTime());
		System.out.println(calendar.getWeekYear());
		System.out.println(Calendar.YEAR);

		calendar.set(2017, 2, 14);
		System.out.println(calendar.getTime());
		calendar.set(Calendar.YEAR, 2018);
		System.out.println(calendar.getTime());

		calendar.roll(Calendar.DATE, true);
		System.out.println(calendar.getTime());

		calendar.roll(Calendar.DATE, 2);
		System.out.println(calendar.getTime());

		Date date = calendar.getTime();
		System.out.println(date.toString());
		System.out.println();
		System.out.println();

		/**
		 * 遍历
		 */
		calendar.set(2017, 6, 26, 0, 0, 0);
		String base_path = "s3://bigdata-east/inveno-data/format-data/topic/impression-reformat/";
		String month, day, hour, minute;

		do {
			month = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MONTH)), 2, "0");
			day = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.DATE)), 2, "0");
			hour = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.HOUR)), 2, "0");
			minute = StringUtils.leftPad(String.valueOf(calendar.get(Calendar.MINUTE)), 2, "0");
			System.out.println(base_path + calendar.get(Calendar.YEAR) + month + day + "/" + hour + "/*_" + hour + minute + "_*");
			calendar.add(Calendar.MINUTE, 10);
		} while (calendar_end.compareTo(calendar) == 1);
	}

}
