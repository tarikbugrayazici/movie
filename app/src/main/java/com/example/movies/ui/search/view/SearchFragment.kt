package com.example.movies.ui.search.view

import android.os.Bundle
import android.view.View
import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.ui.search.adapter.TabLayoutAdapterSearch
import kotlinx.android.synthetic.main.search_fragment_layout.*

class SearchFragment : BaseFragment() {
    override fun getFragmentLayoutId(): Int = R.layout.search_fragment_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayoutAdapterSearch: TabLayoutAdapterSearch = TabLayoutAdapterSearch(activity!!.supportFragmentManager, context!!)
        search_view_pager.adapter = tabLayoutAdapterSearch
        tab_layout_search.setupWithViewPager(search_view_pager)
    }
}