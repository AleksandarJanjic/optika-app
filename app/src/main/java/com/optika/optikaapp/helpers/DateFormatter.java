package com.optika.optikaapp.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {

    public static String getDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = simpleDateFormat.format(calendar.getTime());
        Date date = null;
        try {
             date = simpleDateFormat.parse(formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(formatedDate);
        return formatedDate;
    }
}
