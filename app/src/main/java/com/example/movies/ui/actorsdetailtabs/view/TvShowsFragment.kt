package com.example.movies.ui.actorsdetailtabs.view

import android.os.Bundle

import com.example.movies.core.base.BaseActorsShowFragment

class TvShowsFragment : BaseActorsShowFragment() {

    override fun fetchActorsShows() {
        id?.let { id -> service.fetchActorsTv(id, this) }

    }

    companion object {

        fun newInstance(id: Int): TvShowsFragment {
            val tvShowsFragment = TvShowsFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            tvShowsFragment.arguments = bundle
            return tvShowsFragment
        }
    }


}
