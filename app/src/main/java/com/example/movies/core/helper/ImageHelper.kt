package com.example.movies.core.helper

import com.example.movies.core.util.Constants

object ImageHelper {

    fun getImageUrl(path: String): String {
        return Constants.IMAGE_BASE_PATH + path
    }
}
