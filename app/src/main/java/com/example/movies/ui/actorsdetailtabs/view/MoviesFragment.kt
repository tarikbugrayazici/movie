package com.example.movies.ui.actorsdetailtabs.view

import android.os.Bundle

import com.example.movies.core.base.BaseActorsShowFragment

class MoviesFragment : BaseActorsShowFragment() {

    override fun fetchActorsShows() {
        id?.let { id -> service.fetchActorsMovie(id, this) }

    }

    companion object {

        fun newInstance(id: Int): MoviesFragment {
            val moviesFragment = MoviesFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            moviesFragment.arguments = bundle
            return moviesFragment
        }
    }


}
