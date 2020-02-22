package com.example.movies.ui.trending.view

import com.example.movies.core.base.BaseListFragment

class TabAllFragment : BaseListFragment() {

    override fun fetchRequest() {
        service.fetchTabAllFragment(this)
    }
}
