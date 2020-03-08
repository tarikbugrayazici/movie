package com.example.movies.ui.movie.view

import com.example.movies.core.base.BasePageableFragment

/**
 * Created by Tarik on 16.11.2019.
 */

class MovieFragment : BasePageableFragment() {

    override fun fetchRequest() {
        service.fetchMovies(pagination, this)
    }
}
