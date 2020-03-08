package com.example.movies.core.helper

import android.annotation.SuppressLint

import com.example.movies.core.util.Constants

import java.text.DecimalFormat
import java.text.NumberFormat

@SuppressLint("SimpleDateFormat")
object NumberFormatHelper {

    fun formatNumber(number: Double?): String {
        val formatter = DecimalFormat(Constants.NUMBER_PATTERN)
        return formatter.format(number)
    }

}
