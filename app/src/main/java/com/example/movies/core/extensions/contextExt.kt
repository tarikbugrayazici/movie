package com.example.movies.core.extensions

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.movies.data.entity.MovieCrew
import com.example.movies.ui.actorsdetailtabs.adapter.InfoAdapter


fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) =
        this?.let { Toast.makeText(this, text, duration).show() }
