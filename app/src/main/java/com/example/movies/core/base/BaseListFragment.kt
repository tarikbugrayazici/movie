package com.example.movies.core.base

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.example.movies.R
import com.example.movies.data.entity.BaseEntity
import com.example.movies.data.entity.Movie
import com.example.movies.data.entity.Result
import com.example.movies.data.service.RetroFitService
import com.example.movies.ui.movie.adapter.MovieAdapter

import java.util.ArrayList

import butterknife.BindView
import kotlinx.android.synthetic.main.fragment_layout.*

abstract class BaseListFragment : BaseFragment(), RetroFitService.ResultCallBack {

    var adapter: MovieAdapter? = null
    var list = ArrayList<Movie?>()
    var layoutManager: GridLayoutManager? = null
    var service = RetroFitService()

    abstract fun fetchRequest()

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        fetchRequest()
    }

    fun setRecyclerView() {
        layoutManager = GridLayoutManager(context, 2)
        recyclerView!!.layoutManager = layoutManager
        adapter = MovieAdapter(context, list)
        recyclerView!!.adapter = adapter
    }

    open fun addItemsToList(list: ArrayList<Movie>?) {
        this.list.addAll(list!!)
        adapter?.notifyDataSetChanged()
    }

    open fun setData(list: ArrayList<Movie>?) {
        addItemsToList(list)
    }


    override fun getResult(result: Result<*>) {

        val baseEntityResult: Result<BaseEntity> = result as Result<BaseEntity>
        if (baseEntityResult.data != null) {
            setData(baseEntityResult.data!!.results)
        }
    }
}

