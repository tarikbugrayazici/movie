package com.example.movies.data.entity

import java.util.ArrayList

data class CastDetail(val character: String? = null,
                      val credit_id: String? = null,
                      val release_date: String? = null,
                      val vote_count: Int = 0,
                      val isVideo: Boolean = false,
                      val isAdult: Boolean = false,
                      val vote_average: Number? = null,
                      val title: String? = null,
                      val genre_ids: ArrayList<Int>? = null,
                      val original_language: String? = null,
                      val original_title: String? = null,
                      val popularity: Number? = null,
                      val id: Int = 0,
                      val bacdrop_path: String? = null,
                      val overview: String? = null,
                      val poster_path: String? = null)