package com.example.movies.data.entity

data class Result<T>(var data: T? = null,
                     var errorMessage: String? = null)
