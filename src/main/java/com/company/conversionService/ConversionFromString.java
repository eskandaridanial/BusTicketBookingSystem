package com.company.conversionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversionFromString {

    public Date stringToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public Date stringToTime(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        return format.parse(time);
    }
}
