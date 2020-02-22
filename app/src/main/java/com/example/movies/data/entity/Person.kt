package com.example.movies.data.entity

import java.util.ArrayList

class Person {
    val isAdult: Boolean = false
    val gender: Int = 0
    val name: String? = null
    val id: Int = 0
    var knownFors: ArrayList<KnownFor>? = null
        internal set
    val knownForDeparment: String? = null
    val profile_path: String? = null
    val popularty: Number? = null
    val mediaType: String? = null
}
