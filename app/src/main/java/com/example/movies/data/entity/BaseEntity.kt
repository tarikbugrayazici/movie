package com.example.movies.data.entity

import java.util.ArrayList

/**
 * Created by Tarik on 16.11.2019.
 */

class BaseEntity {
    val page: Int = 0
    val total_results: Int = 0
    val total_pages: Int = 0
    val results: ArrayList<Movie>? = null
}
