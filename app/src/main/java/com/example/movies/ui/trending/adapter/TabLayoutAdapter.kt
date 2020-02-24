package com.example.movies.ui.trending.adapter


import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.movies.ui.trending.view.TabAllFragment
import com.example.movies.ui.trending.view.TabMovieFragment
import com.example.movies.ui.trending.view.TabPersonFragment
import com.example.movies.ui.trending.view.TabTvFragment


class TabLayoutAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return TabAllFragment()
            1 -> return TabMovieFragment()
            2 -> return TabTvFragment()
            3 -> return TabPersonFragment()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "All"
            1 -> return "Movie"
            2 -> return "Tv"
            3 -> return "Person"
        }
        return null
    }
}
