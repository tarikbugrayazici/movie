package com.example.movies.core.helper;

import android.annotation.SuppressLint;

import com.example.movies.core.util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateFormatHelper {

    public static String formatDate(String date){
        SimpleDateFormat spf = new SimpleDateFormat(Constants.UNFORMATTED_DATE);
        try {
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat(Constants.FORMATTED_DATE);
            return spf.format(newDate);
        } catch (ParseException e) {
           return date;
        }
    }
}
