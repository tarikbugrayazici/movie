package com.example.movies.ui.trending.view

import com.example.movies.core.base.BaseListFragment


class TabMovieFragment : BaseListFragment() {


    override fun fetchRequest() {
        service.fetchTabMovie(this)
    }
}
