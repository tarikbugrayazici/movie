package com.example.movies.ui.detailtabs.view

import android.os.Bundle

import com.example.movies.core.base.BasePageableFragment
import com.example.movies.core.base.BaseSimilarPageableFragment

class SimilarFragment : BasePageableFragment() {
    override fun fetchRequest() {
        movieId?.let { id -> service.fetchSimilarMovies(id, this, pagination) }

    }

    companion object {

        fun newInstance(id: Int): SimilarFragment {
            val fragment = SimilarFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment

        }
    }


}


