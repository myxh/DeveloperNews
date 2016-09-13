package com.myxh.developernews.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by asus on 2016/8/11.
 */
public class DateUtil {

    public static String parseDate(String stringDate) {
        Date date = new Date(stringDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String day = simpleDateFormat.format(date);
        return day;
    }

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return ""+year+"."+month+"."+day;
    }
}
