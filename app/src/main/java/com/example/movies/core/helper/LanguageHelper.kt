package com.example.movies.core.helper

import android.annotation.SuppressLint

import com.example.movies.core.util.Constants

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("SimpleDateFormat")
object LanguageHelper {

    fun formatLanguage(language: String): String {
        val loc = Locale(language)
        return loc.getDisplayLanguage(loc)
    }

}
