package com.example.movies.data.entity

import java.util.ArrayList

data class Media(val popularity: Float? = null,
            val vote_count: Int? = null,
            val video: Boolean? = null,
            val poster_path: String? = null,
            val id: Int? = null,
            val adult: Boolean? = null,
            val backdrop_path: String? = null,
            val original_language: String? = null,
            val original_title: String? = null,
            val genre_ids: ArrayList<Int>? = null,
            val title: String? = null,
            val vote_average: Float? = null,
            val overview: String? = null,
            val release_date: String? = null)
