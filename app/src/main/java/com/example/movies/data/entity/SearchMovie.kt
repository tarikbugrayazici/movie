package com.example.movies.data.entity

import java.util.ArrayList

data class SearchMovie(val popularty: Number? = null,
                       val id: Int = 0,
                       val isVideo: Boolean = false,
                       val voteCount: Int = 0,
                       val voteAverage: Int = 0,
                       val title: String? = null,
                       val releaseDate: String? = null,
                       val originalLanguage: String? = null,
                       val originalTitle: String? = null,
                       val genreids: ArrayList<Int>? = null,
                       val backdropPath: String? = null,
                       val isAdult: Boolean = false,
                       val overview: String? = null,
                       val poster_path: String? = null)
