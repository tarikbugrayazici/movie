package com.example.movies.ui.detailtabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.navigation.Navigation
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Cast
import com.example.movies.data.enums.GenderImage

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import com.example.movies.core.extensions.inflate
import com.example.movies.core.extensions.loadFromUrl
import de.hdodenhof.circleimageview.CircleImageView

class ActorsAdapter(private val context: Context, private val list: ArrayList<Cast>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            return ActorsViewHolder(parent.inflate(R.layout.oyuncular_item))
        } else {
            return LoadingViewHolder(parent.inflate(R.layout.progress_bar))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ActorsViewHolder) {
            val (_, character, _, gender, id, name, _, profile_path) = list[position]

            if (profile_path != null) {
                holder.img_castt!!.loadFromUrl(Constants.IMAGE_BASE_PATH + profile_path)
            } else {
                var profilePhoto = R.drawable.male

                if (gender == GenderImage.FEMALE.genderImage) {
                    profilePhoto = R.drawable.female
                }
                holder.img_castt!!.setImageResource(profilePhoto)
            }
            holder.oyuncu_text_name!!.text = name
            holder.oyuncu_text_movie_name!!.text = character
            holder.itemView.setOnClickListener { Navigation.startActorsDetailActivity(context, id) }

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

    interface FragmentMovieListener {
        fun onInputSent(input: CharSequence)
    }

    inner class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_castt = itemView.findViewById<ImageView>(R.id.img_castt)
        val oyuncu_text_name = itemView.findViewById<TextView>(R.id.oyuncu_text_name)
        val oyuncu_text_movie_name = itemView.findViewById<TextView>(R.id.oyuncu_text_movie_name)
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById<View>(R.id.progress) as ProgressBar
        }
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        //ProgressBar would be displayed

    }
}