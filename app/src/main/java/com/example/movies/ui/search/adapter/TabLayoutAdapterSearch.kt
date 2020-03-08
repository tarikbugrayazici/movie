package com.example.movies.ui.search.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.movies.ui.search.view.SearchTabMovieFragment
import com.example.movies.ui.search.view.SearchTabTvFragment


class TabLayoutAdapterSearch(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return SearchTabMovieFragment()
            1 -> return SearchTabTvFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Movie"
            1 -> return "Tv"
        }
        return null
    }
}

