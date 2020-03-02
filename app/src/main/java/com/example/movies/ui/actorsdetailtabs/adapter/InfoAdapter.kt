package com.example.movies.ui.actorsdetailtabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.extensions.inflate
import com.example.movies.core.extensions.loadFromUrl
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.ActorsPhotos
import java.util.*

class InfoAdapter(private val context: Context, private val list: ArrayList<ActorsPhotos>?) :
        RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): InfoViewHolder {
        return InfoViewHolder(parent.inflate(R.layout.actors_photo_item))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val actorsPhotos: ActorsPhotos = list!![position];
        holder.photo.loadFromUrl(Constants.IMAGE_BASE_PATH + actorsPhotos.file_path)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo = itemView.findViewById<ImageView>(R.id.photo)
    }
}
