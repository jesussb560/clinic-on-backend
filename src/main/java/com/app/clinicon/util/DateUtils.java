package com.app.clinicon.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import java.sql.Timestamp;

@Component
public class DateUtils {

    public Timestamp atEndOfDay(Date date) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        
        Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

        return timestamp;
    }

    public Timestamp atStartOfDay(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        Timestamp timestamp = new Timestamp(calendar.getTime().getTime());

        return timestamp;
    }

    public Date[] getDaysOfWeek(Date refDate, int firstDayOfWeek) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDate);
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        Date[] daysOfWeek = new Date[7];
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysOfWeek;
    }
    
}
