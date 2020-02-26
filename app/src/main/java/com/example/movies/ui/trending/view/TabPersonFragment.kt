package com.example.movies.ui.trending.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.data.entity.Person
import com.example.movies.data.entity.Result
import com.example.movies.data.entity.TabPersonEntity
import com.example.movies.data.service.RetroFitService
import com.example.movies.data.service.RetroFitService.ResultCallBack
import com.example.movies.ui.trending.adapter.TabPersonAdapter
import kotlinx.android.synthetic.main.fragment_layout.*
import java.util.*

class TabPersonFragment : BaseFragment() {
    private var adapter: TabPersonAdapter? = null
    private val people = ArrayList<Person?>()
    private var layoutManager: GridLayoutManager? = null
    private var isLoadingShowed = false
    private var pagination = 1
    private var sizeOfPage = 0
    var service = RetroFitService()
    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        initScrollListener()
        fetchPerson()
    }

    private fun initScrollListener() {
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoadingShowed) {
                    if (layoutManager != null && layoutManager!!.findLastCompletelyVisibleItemPosition() == people.size - 1) {
                        loadMore()
                        isLoadingShowed = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        pagination++
        people.add(null)
        adapter!!.notifyDataSetChanged()
        fetchPerson()
    }

    private fun fetchPerson() {
        val serviceCallBack: ResultCallBack = object : ResultCallBack {
            override fun getResult(result: Result<*>) {
                val tabPersonEntityResult: Result<TabPersonEntity> = result as Result<TabPersonEntity>
                if (tabPersonEntityResult.data != null ||
                        (if (result.data != null) tabPersonEntityResult.data!!.results else null) != null
                        && (pagination == 1 || sizeOfPage == tabPersonEntityResult.data!!.results!!.size)) {
                    setData(result.data!!.results)
                } else {
                    setLoadingCase()
                }
            }
        }
        service.fetchPerson(serviceCallBack)
    }

    private fun setRecyclerView() {
        layoutManager = GridLayoutManager(context, 2)
        recyclerView!!.layoutManager = layoutManager
        adapter = TabPersonAdapter(context!!, people)
        recyclerView!!.adapter = adapter
    }

    private fun setLoadingCase() {
        if (isLoadingShowed) {
            people.removeAt(people.size - 1)
            adapter!!.notifyDataSetChanged()
            isLoadingShowed = false
        }
    }

    private fun addItemsToList(list: ArrayList<Person>?) {
        sizeOfPage = list!!.size
        people.addAll(list)
        adapter!!.notifyDataSetChanged()
    }

    private fun setData(list: ArrayList<Person>?) {
        setLoadingCase()
        addItemsToList(list)
    }
}