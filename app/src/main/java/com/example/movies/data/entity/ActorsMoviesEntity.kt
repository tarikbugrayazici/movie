package com.example.movies.data.entity

import java.util.ArrayList

data class ActorsMoviesEntity(
        val cast: ArrayList<CastDetail>? = null,
        val crew: ArrayList<CrewDetail>? = null,
        val id: Int = 0) 


