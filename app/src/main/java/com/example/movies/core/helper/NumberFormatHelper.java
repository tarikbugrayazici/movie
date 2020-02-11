package com.example.movies.core.helper;

import android.annotation.SuppressLint;

import com.example.movies.core.util.Constants;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@SuppressLint("SimpleDateFormat")
public class NumberFormatHelper {

    public static String formatNumber(Double number) {
        NumberFormat formatter = new DecimalFormat(Constants.NUMBER_PATTERN);
        String formattedNumber = formatter.format(number);
        return formattedNumber;
    }

}
