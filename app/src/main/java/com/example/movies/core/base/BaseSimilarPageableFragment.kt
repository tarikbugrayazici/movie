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
import com.example.movies.ui.detailtabs.adapter.RecommendedAdapter
import kotlinx.android.synthetic.main.fragment_layout.*
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseSimilarPageableFragment : BaseFragment(), RetroFitService.ResultCallBack {

    var adapter: RecommendedAdapter? = null
    var layoutManager: GridLayoutManager? = null
    var movies: ArrayList<Movie?> = ArrayList()
    var isLoadingShowed = false
    var pagination = 1
    var sizeOfPage = 0
    var movieId: Int = 0
    var service = RetroFitService()

    abstract fun fetchRequest()

    override fun getFragmentLayoutId(): Int? {
        return R.layout.fragment_layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            movieId = arguments!!.getInt("id")
            setRecyclerView()
            initScrollListener()
            fetchRequest()
        }
    }

    private fun initScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoadingShowed) {
                    if (layoutManager != null && layoutManager!!.findLastCompletelyVisibleItemPosition() == movies.size - 1) {
                        loadMore()
                        isLoadingShowed = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        pagination++
        movies.add(null)
        adapter?.notifyDataSetChanged()
        fetchRequest()
    }


    private fun setLoadingCase() {
        if (isLoadingShowed) {
            movies.removeAt(movies.size - 1)
            adapter?.notifyDataSetChanged()
            isLoadingShowed = false
        }
    }

    private fun setRecyclerView() {
        layoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = layoutManager
        adapter = RecommendedAdapter(context, movies)
        recyclerView.adapter = adapter

    }

    private fun addItemsToList(list: ArrayList<Movie>) {
        sizeOfPage = list.size
        movies.addAll(list)
        adapter?.notifyDataSetChanged()
    }

    private fun setData(list: ArrayList<Movie>?) {
        setLoadingCase()
        addItemsToList(list!!)
    }

    override fun getResult(result: Result<*>) {
        val baseEntityResult : Result<BaseEntity> = result as Result<BaseEntity>
        if (baseEntityResult.data != null ||
                (if (baseEntityResult.data != null) baseEntityResult.data!!.results else null) != null
                && (pagination == 1 || sizeOfPage == baseEntityResult.data!!.results!!.size)) {
            setData(baseEntityResult.data!!.results)
        } else {
            setLoadingCase()
        }

    }
}

