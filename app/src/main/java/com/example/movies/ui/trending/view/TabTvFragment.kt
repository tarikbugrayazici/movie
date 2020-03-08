package com.example.movies.ui.trending.view

import com.example.movies.core.base.BaseListFragment


class TabTvFragment : BaseListFragment() {

    override fun fetchRequest() {
        service.fetchTabTv(this)
    }
}
