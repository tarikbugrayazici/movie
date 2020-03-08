package com.example.movies.ui.search.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.data.entity.BaseEntity
import com.example.movies.data.entity.Movie
import com.example.movies.data.entity.Result
import com.example.movies.data.service.RetroFitService
import com.example.movies.ui.search.adapter.SearchTabMovieAdapter
import kotlinx.android.synthetic.main.fragment_search_tab_tv.*

class SearchTabTvFragment : BaseFragment() {
    private var adapter: SearchTabMovieAdapter? = null
    var service = RetroFitService()
    private var text: String? = null
    override fun getFragmentLayoutId(): Int = R.layout.fragment_search_tab_tv

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_tv_recycler_view!!.layoutManager = (GridLayoutManager(context, 2))
        edit_tv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length > 3) {
                    text = s.toString()
                    fetchNamesTv(text!!)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun fetchNamesTv(text: String) {
        val serviceCallBack: RetroFitService.ResultCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val baseEntityResult: Result<BaseEntity> = result as Result<BaseEntity>
                if (baseEntityResult.data != null) {
                    setRecyclerView(baseEntityResult.data!!.results);
                } else {
                    Toast.makeText(activity, baseEntityResult.errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }
        service.fetchTvNames(serviceCallBack, text);
    }

    private fun setRecyclerView(list: ArrayList<Movie>?) {
        adapter = SearchTabMovieAdapter(context!!, list!!)
        search_tv_recycler_view.adapter = adapter
    }
}