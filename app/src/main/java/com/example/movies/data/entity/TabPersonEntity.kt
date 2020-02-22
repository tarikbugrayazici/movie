package com.example.movies.data.entity

import java.util.ArrayList

data class TabPersonEntity(val page: Int? = null,
                           val results: ArrayList<Person>? = null,
                           val total_pages: Int? = null,
                           val total_results: Int = 0)
