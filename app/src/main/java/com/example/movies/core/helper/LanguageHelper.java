package com.example.movies.core.helper;

import android.annotation.SuppressLint;

import com.example.movies.core.util.Constants;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class LanguageHelper {

    public static String formatLanguage(String language) {
        Locale loc = new Locale(language);
        String name = loc.getDisplayLanguage(loc);
        return name;
    }

}
