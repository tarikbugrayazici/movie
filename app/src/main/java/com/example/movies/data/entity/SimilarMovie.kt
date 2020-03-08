package com.example.movies.data.entity

import java.util.ArrayList

data class SimilarMovie(val poster_path: String? = null,
                        val isAdult: Boolean = false,
                        val overview: String? = null,
                        val release_date: String? = null,
                        val genre_ids: ArrayList<Int>? = null,
                        val id: Int? = null,
                        val original_title: String? = null,
                        val original_language: String? = null,
                        val title: String? = null,
                        val backdrop_path: String? = null,
                        val popularity: Number? = null,
                        val vote_count: Int = 0,
                        val isVideo: Boolean = false,
                        val vote_average: Number? = null)
