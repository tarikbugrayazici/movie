package com.example.movies.ui.tv.view

import com.example.movies.core.base.BasePageableFragment

/**
 * Created by Tarik on 16.11.2019.
 */

class TvFragment : BasePageableFragment() {

    override fun fetchRequest() {
        service.fetchTv(pagination, this)
    }
}


