package com.example.movies.ui.detail

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.movies.ui.detailtabs.view.ActorsFragment

import com.example.movies.ui.detailtabs.view.InfoFragment
import com.example.movies.ui.detailtabs.view.RecommendedFragment
import com.example.movies.ui.detailtabs.view.SimilarFragment

class DetailTabAdapter(fm: FragmentManager, private val context: Context, private val id: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return InfoFragment.newInstance(id)
            1 -> return ActorsFragment.newInstance(id)
            2 -> return SimilarFragment.newInstance(id)
            3 -> return RecommendedFragment.newInstance(id)
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                var bilgiler = "Bilgiler"
                bilgiler = bilgiler.substring(0, 1).toUpperCase() + bilgiler.substring(1).toLowerCase()
                return bilgiler
            }
            1 -> {
                var oyuncular = "Oyuncular"
                oyuncular = oyuncular.substring(0, 1).toUpperCase() + oyuncular.substring(1).toLowerCase()
                return oyuncular
            }
            2 -> {
                var benzeri = "Benzeri"
                benzeri = benzeri.substring(0, 1).toUpperCase() + benzeri.substring(1).toLowerCase()
                return benzeri
            }
            3 -> {
                var ilgili = "İlgili"
                ilgili = ilgili.substring(0, 1).toUpperCase() + ilgili.substring(1).toLowerCase()
                return ilgili
            }
        }
        return null
    }
}
