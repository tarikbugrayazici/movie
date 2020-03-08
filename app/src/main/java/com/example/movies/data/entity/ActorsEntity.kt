package com.example.movies.data.entity

import java.util.ArrayList

data class ActorsEntity(
        val birthday: String? = null,
        val known_for_department: String? = null,
        val deadthday: String? = null,
        val id: Int = 0,
        val name: String? = null,
        val also_known_as: ArrayList<String>? = null,
        val gender: Int = 0,
        val biography: String? = null,
        val popularity: Number? = null,
        val place_of_birth: String? = null,
        val profile_path: String? = null,
        val isAdult: Boolean = false,
        val imdb_id: String? = null,
        val homepage: String? = null
)
