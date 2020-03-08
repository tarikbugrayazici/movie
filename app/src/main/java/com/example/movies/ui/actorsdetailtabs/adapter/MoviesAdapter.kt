package com.example.movies.ui.actorsdetailtabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.CastDetail
import java.util.*

class MoviesAdapter(private val context: Context, private val list: ArrayList<CastDetail>, private val layout: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == GRID) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_cardview, viewGroup, false)
            return GridLayoutViewHolder(view)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_cardview_linear, viewGroup, false)
            return LinearLayoutViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val castDetail = list[position]
        if (viewHolder is GridLayoutViewHolder) {
            Glide
                    .with(context)
                    .load(Constants.IMAGE_BASE_PATH + castDetail.poster_path!!)
                    .centerCrop()
                    .into(viewHolder.img_view_card_view!!)
        } else if (viewHolder is LinearLayoutViewHolder) {
            Glide
                    .with(context)
                    .load(Constants.IMAGE_BASE_PATH + castDetail.poster_path!!)
                    .centerCrop()
                    .into(viewHolder.img_view_card_view_linear!!)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return this.layout
    }

    inner class LinearLayoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_view_card_view_linear = itemView.findViewById<ImageView>(R.id.img_view_card_view_linear)
    }

    inner class GridLayoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_view_card_view = itemView.findViewById<ImageView>(R.id.img_view_card_view)

    }

    companion object {

        private val GRID = 0
        private val LINEAR = 1
    }
}
