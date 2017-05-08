package calendar;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

	public static void main(String[] args) {
        
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
	}

}

