package com.example.movies.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}