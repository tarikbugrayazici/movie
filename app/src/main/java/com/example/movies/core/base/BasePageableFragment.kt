package com.example.movies.core.base

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View

import com.example.movies.data.entity.BaseEntity
import com.example.movies.data.entity.Movie
import com.example.movies.data.entity.Result
import com.example.movies.data.service.RetroFitService

import java.util.ArrayList

abstract class BasePageableFragment : BaseListFragment(), RetroFitService.ResultCallBack {

    private var isLoadingShowed = false
    var pagination = 1
    var sizeOfPage = 0
    var movieId: Int? = null

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
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                        loadMore()
                        isLoadingShowed = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        pagination++
        list.add(null)
        adapter.notifyDataSetChanged()
        fetchRequest()
    }

    fun setLoadingCase() {
        if (isLoadingShowed) {
            list.removeAt(list.size - 1)
            adapter.notifyDataSetChanged()
            isLoadingShowed = false
        }
    }

    override fun addItemsToList(list: ArrayList<Movie>) {
        sizeOfPage = list.size
        super.addItemsToList(list)
    }

    override fun setData(list: ArrayList<Movie>?) {
        setLoadingCase()
        super.setData(list)
    }

    override fun getResult(result: Result<*>) {
        val baseEntityResult: Result<BaseEntity> = result as Result<BaseEntity>
        if (baseEntityResult.data != null ||
                (if (result.data != null) baseEntityResult.data!!.results else null) != null
                && (pagination == 1 || sizeOfPage == baseEntityResult.data!!.results!!.size)) {
            setData(result.data!!.results)
        } else {
            setLoadingCase()
        }
    }
}

