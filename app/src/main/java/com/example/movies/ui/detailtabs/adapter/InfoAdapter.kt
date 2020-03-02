package com.example.movies.ui.detailtabs.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Trailer

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import com.example.movies.core.extensions.inflate
import com.example.movies.core.extensions.loadFromUrl

class InfoAdapter(private val context: Context, private val list: ArrayList<Trailer>) :
        RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): InfoViewHolder {
        return InfoViewHolder(parent.inflate(R.layout.layout_item_trailer))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val trailer = list[position]
        holder.imgTrailer!!.loadFromUrl(Constants.TRAILER_BASE_PATH + trailer.key)
        holder.trailer!!.text = trailer.name
        holder.imgTrailer!!.setOnClickListener { v ->
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constants.TRAILER_BASE_PATH + trailer.key!!))
            v.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgTrailer = itemView.findViewById<ImageView>(R.id.imgTrailer)
        var trailer = itemView.findViewById<TextView>(R.id.trailer)
    }
}
