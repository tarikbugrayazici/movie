package com.example.movies.ui.trending.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.navigation.Navigation
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Movie

import java.util.ArrayList


class TabAllAdapter(private val context: Context, private val list: ArrayList<Movie>) : RecyclerView.Adapter<TabAllAdapter.TabAllAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabAllAdapterHolder {
        return TabAllAdapterHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item, parent, false))
    }

    override fun onBindViewHolder(holder: TabAllAdapterHolder, position: Int) {
        val (_, _, _, poster_path, id) = list[position]
        Glide.with(context).load(Constants.IMAGE_BASE_PATH + poster_path!!)
                .centerCrop().into(holder.img_view!!)
        holder.itemView.setOnClickListener { Navigation.startDetailActivity(context, id) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TabAllAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_view = itemView.findViewById<ImageView>(R.id.img_view)


    }
}
