package com.example.movies.ui.trending.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View

import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.ui.trending.adapter.TabLayoutAdapter

import butterknife.BindView


/**
 * Created by Tarik on 16.11.2019.
 */

class TrendingFragment : BaseFragment() {
    @BindView(R.id.tab_layout)
    internal var tabLayout: TabLayout? = null
    @BindView(R.id.view_pager)
    internal var viewPager: ViewPager? = null

    override fun getFragmentLayoutId(): Int {
        return R.layout.trending_fragment_layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayoutAdapter = TabLayoutAdapter(activity!!
                .supportFragmentManager, context)
        viewPager!!.adapter = tabLayoutAdapter
        tabLayout!!.setupWithViewPager(viewPager)


    }

}

