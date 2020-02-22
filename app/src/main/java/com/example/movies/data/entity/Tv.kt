package com.example.movies.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Tv(
        val original_name: String?,
        val genre_ids: ArrayList<Int>? = null,
        val name: String?,
        val popularty: Number,
        val origin_country: ArrayList<String>?,
        val vote_count: Int,
        val first_air_date: String?,
        val backdrop_path: String?,
        val original_language: String?,
        val id: Int,
        val vote_average: Number,
        val overview: String?,
        val poster_path: String?) : Parcelable