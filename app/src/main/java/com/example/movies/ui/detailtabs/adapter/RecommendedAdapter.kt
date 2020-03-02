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
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Movie

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import com.example.movies.core.extensions.inflate
import com.example.movies.core.extensions.loadFromUrl

class RecommendedAdapter(private val context: Context?, private val list: ArrayList<Movie?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        if (position == VIEW_TYPE_ITEM) {
            return RecommendedViewHolder(parent.inflate(R.layout.layout_item_cardview))
        } else {
            return LoadingViewHolder(parent.inflate(R.layout.progress_bar))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecommendedViewHolder) {
            val recommendedMovie: Movie? = list[position]
            holder.img_view_card_view.loadFromUrl(Constants.IMAGE_BASE_PATH + recommendedMovie!!.poster_path!!)
            holder.itemView.setOnClickListener { Navigation.startDetailActivity(context!!, recommendedMovie!!.id) }

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

    inner class RecommendedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_view_card_view = itemView.findViewById<ImageView>(R.id.img_view_card_view)

    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progress)
        }
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        //ProgressBar would be displayed

    }
}
