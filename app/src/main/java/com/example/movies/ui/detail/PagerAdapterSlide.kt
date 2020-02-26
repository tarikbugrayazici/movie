package com.example.movies.ui.detail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Backdrops

import java.util.ArrayList


class PagerAdapterSlide(private val context: Context, private val list: ArrayList<Backdrops>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val (_, file_path) = list[position]
        val layoutInflater = LayoutInflater.from(context)
        val viewGroup = layoutInflater.inflate(R.layout.view_pager_item, container, false) as ViewGroup
        container.addView(viewGroup)
        val imageView = viewGroup.findViewById<ImageView>(R.id.image)
        Glide.with(context).load(Constants.IMAGE_BASE_PATH + file_path!!).centerCrop().into(imageView)
        return viewGroup
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` === view
    }

    override fun getCount(): Int {
        return list.size
    }


}
