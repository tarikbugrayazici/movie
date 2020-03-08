package com.example.movies.ui.detailtabs.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Crew
import com.example.movies.data.enums.GenderImage


import java.util.ArrayList

class CrewsDialogAdapter(private val context: Context, private val list: ArrayList<Crew>) : RecyclerView.Adapter<CrewsDialogAdapter.CrewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewsViewHolder {
        return CrewsViewHolder(LayoutInflater.from(context).inflate(R.layout.crews_layout_item, parent, false))
    }

    override fun onBindViewHolder(holder: CrewsDialogAdapter.CrewsViewHolder, position: Int) {
        val (_, department, gender, _, _, name, profile_path) = list[position]
        if (profile_path != null) {
            Glide.with(context)
                    .load(Constants.IMAGE_BASE_PATH + profile_path)
                    .placeholder(R.drawable.female)
                    .centerCrop()
                    .into(holder.imageView)
        } else {
            var profilePhoto = R.drawable.male

            if (gender == GenderImage.MALE.genderImage) {
                profilePhoto = R.drawable.male
            } else if (gender == GenderImage.FEMALE.genderImage) {
                profilePhoto = R.drawable.female
            }
            holder.imageView.setImageResource(profilePhoto)
        }
        holder.crewName.text = name
        holder.crewDepartment.text = department
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CrewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imageView: ImageView
        internal var crewName: TextView
        internal var crewDepartment: TextView

        init {
            imageView = itemView.findViewById(R.id.img_crew)
            crewName = itemView.findViewById(R.id.crew_text_name)
            crewDepartment = itemView.findViewById(R.id.crew_department)
        }
    }
}
