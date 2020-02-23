package com.example.movies.ui.actorsDetail

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
import com.example.movies.data.entity.Media

import java.util.ArrayList


class PagerAdapterSlideActor(private val context: Context, private val list: ArrayList<Media>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val (_, _, _, poster_path) = list[position]
        val layoutInflater = LayoutInflater.from(context)
        val imgUrl = Constants.IMAGE_BASE_PATH + poster_path!!
        val viewGroup = layoutInflater.inflate(R.layout.view_pager_item, container, false) as ViewGroup
        container.addView(viewGroup)
        val imageView = viewGroup.findViewById<ImageView>(R.id.image)
        Glide.with(context).load(imgUrl).centerCrop().into(imageView)
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
