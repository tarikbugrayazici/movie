package com.example.movies.ui.detailtabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar

import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.navigation.Navigation
import com.example.movies.data.entity.Movie

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife

class SimilarAdapter(private val context: Context, private val list: ArrayList<Movie>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_cardview, parent, false)
            return SimilarViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.progress_bar, parent, false)
            return LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SimilarViewHolder) {
            val (_, _, _, poster_path, id) = list!![position]
            val url = "https://image.tmdb.org/t/p/w500"
            Glide.with(context)
                    .load(url + poster_path!!)
                    .into(holder.img_view_card_view!!)

            holder.itemView.setOnClickListener { Navigation.startDetailActivity(context, id) }
        } else if (holder is LoadingViewHolder) {
            showLoadingView(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (list!![position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    inner class SimilarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_view_card_view = itemView.findViewById<ImageView>(R.id.img_view_card_view)

    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById<View>(R.id.progress) as ProgressBar
        }
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {

    }
}
