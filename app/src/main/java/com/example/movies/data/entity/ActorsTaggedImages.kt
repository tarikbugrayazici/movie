package com.example.movies.data.entity

import java.util.ArrayList

data class ActorsTaggedImages(val id: Int = 0,
                              val page: Int = 0,
                              val total_results: Int = 0,
                              val results: ArrayList<ActorsResult>? = null,
                              val total_pages: Int = 0)
