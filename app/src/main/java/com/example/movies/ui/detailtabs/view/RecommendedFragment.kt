package com.example.movies.ui.detailtabs.view

import android.os.Bundle

import com.example.movies.core.base.BasePageableFragment
import com.example.movies.core.base.BaseSimilarPageableFragment


class RecommendedFragment : BasePageableFragment() {

    override fun fetchRequest() {
        movieId?.let {id ->
            service.fetchRecommendedMovie(id, this, pagination)
        }
    }

    companion object {

        fun newInstance(id: Int): RecommendedFragment {
            val fragment = RecommendedFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }


}
