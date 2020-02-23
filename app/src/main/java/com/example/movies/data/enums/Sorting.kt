package com.example.movies.data.enums

enum class Sorting private constructor(val value: Int) {
    BY_RECOMMENDED(0),
    BY_NAME(1),
    BY_CHARACTER(2)
}
