package com.example.movies.ui.movie.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.extensions.inflate
import com.example.movies.core.extensions.loadFromUrl
import com.example.movies.core.navigation.Navigation.startDetailActivity
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Movie
import java.util.*

class MovieAdapter(private val context: Context, private val list: ArrayList<Movie?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            MovieViewHolder(parent.inflate(R.layout.layout_item))
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.progress_bar, parent, false)
            LoadingViewHolder(parent.inflate(R.layout.progress_bar))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            val movie = list[position]
            holder.img_view.loadFromUrl(Constants.IMAGE_BASE_PATH + movie!!.poster_path)
            holder.itemView.setOnClickListener { startDetailActivity(context, movie.id) }
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    inner class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val img_view = itemView!!.findViewById<ImageView>(R.id.img_view)

    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById<View>(R.id.progress) as ProgressBar
        }
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) { //ProgressBar would be displayed
    }

}