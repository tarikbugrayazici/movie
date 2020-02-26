package com.example.movies.ui.actorsDetail

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.movies.ui.actorsdetailtabs.view.InfoFragment

import com.example.movies.ui.actorsdetailtabs.view.MoviesFragment
import com.example.movies.ui.actorsdetailtabs.view.TvShowsFragment

class ActorsDetailTabAdapter(fm: FragmentManager, private val context: Context, private val id: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return InfoFragment.newInstance(id)
            1 -> return MoviesFragment.newInstance(id)
            2 -> return TvShowsFragment.newInstance(id)
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                var bilgiler = "Bilgiler"
                bilgiler = bilgiler.substring(0, 1).toUpperCase() + bilgiler.substring(1).toLowerCase()
                return bilgiler
            }
            1 -> {
                var filmler = "Filmler"
                filmler = filmler.substring(0, 1).toUpperCase() + filmler.substring(1).toLowerCase()
                return filmler
            }
            2 -> {
                var tv = "TV Programları"
                tv = tv.substring(0, 1).toUpperCase() + tv.substring(1).toLowerCase()
                return tv
            }
        }
        return null
    }
}
