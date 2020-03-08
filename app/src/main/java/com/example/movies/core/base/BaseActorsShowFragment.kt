package com.example.movies.core.base

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast

import com.example.movies.R
import com.example.movies.data.entity.ActorsMoviesEntity
import com.example.movies.data.entity.CastDetail
import com.example.movies.data.entity.Result
import com.example.movies.data.service.RetroFitService
import com.example.movies.ui.actorsdetailtabs.adapter.MoviesAdapter

import java.util.ArrayList

import butterknife.BindView
import kotlinx.android.synthetic.main.actors_movies.*

abstract class BaseActorsShowFragment : BaseFragment(), RetroFitService.ResultCallBack {

    var adapter: MoviesAdapter? = null
    var castDetails = ArrayList<CastDetail>()
    var id: Int? = null
    var isGridLayoutManager = true
    var service = RetroFitService()

    abstract fun fetchActorsShows()


    override fun getFragmentLayoutId(): Int {
        return R.layout.actors_movies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            id = arguments!!.getInt("id")
            fetchActorsShows()
        }

        view.setOnClickListener {
            isGridLayoutManager = !isGridLayoutManager
            setRecyclerView(isGridLayoutManager)
        }
    }


    private fun setData(list: ArrayList<CastDetail>?) {
        castDetails.addAll(list!!)
        setRecyclerView(isGridLayoutManager)
    }

    fun setRecyclerView(isGridLayoutManager: Boolean) {
        if (isGridLayoutManager) {
            recyclerViewActors.layoutManager = GridLayoutManager(context, 3)
        } else {
            recyclerViewActors.layoutManager = LinearLayoutManager(context)
        }


        var layoutType = 0
        if (!isGridLayoutManager) {
            layoutType = 1
        }
        adapter = MoviesAdapter(context!!, castDetails, layoutType)
        recyclerViewActors.adapter = adapter
    }

    override fun getResult(result: Result<*>) {
        val actorsMoviesEntityResult: Result<ActorsMoviesEntity> = result as Result<ActorsMoviesEntity>
        if (actorsMoviesEntityResult.data != null) {
            setData(actorsMoviesEntityResult.data!!.cast)
        } else {
            Toast.makeText(activity, actorsMoviesEntityResult.errorMessage, Toast.LENGTH_LONG).show()
        }

    }
}
