package com.example.movies.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/*fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int): T {
    return DataBindingUtil.inflate(layoutId, this, true)
}*/

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}