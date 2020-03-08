package com.example.movies.core.navigation

import android.content.Context
import android.content.Intent
import com.example.movies.ui.actorsDetail.ActorsDetailActivity

import com.example.movies.ui.detail.DetailActivity

object Navigation {

    fun startDetailActivity(context: Context, id: Int) {
        val i = Intent(context, DetailActivity::class.java)
        i.putExtra("id", id)
        context.startActivity(i)
    }

    fun startActorsDetailActivity(context: Context, id: Int) {
        val i = Intent(context, ActorsDetailActivity::class.java)
        i.putExtra("actor", id)
        context.startActivity(i)
    }
}
