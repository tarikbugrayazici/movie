package com.example.movies.data.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import java.util.ArrayList

/**
 * Created by Tarik on 16.11.2019.
 */
@Parcelize
data class Movie(val popularity: Number,
                 val vote_count: Int,
                 val isVideo: Boolean,
                 val poster_path: String?,
                 val id: Int,
                 val isAdult: Boolean,
                 val backdrop_path: String?,
                 val original_language: String?,
                 val original_title: String?,
                 val genre_ids: ArrayList<Int>,
                 val title: String?,
                 val vote_average: Number,
                 val overview: String?,
                 val release_date: String?) : Parcelable