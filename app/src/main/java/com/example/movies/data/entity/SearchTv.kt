package com.example.movies.data.entity

import java.util.ArrayList

data class SearchTv(val popularity: Number? = null,
               val id: Int? = null,
               val name: String? = null,
               val vote_count: Int = 0,
               val vote_average: Number? = null,
               val first_air_date: String? = null,
               val original_name: String? = null,
               val original_language: String? = null,
               val genreids: ArrayList<Int>? = null,
               val backdrop_path: String? = null,
               val overview: String? = null,
               val poster_path: String? = null,
               val origin_country: ArrayList<String>? = null)
