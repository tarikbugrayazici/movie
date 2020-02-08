package com.example.movies.ui.detailtabs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.Crew;
import com.example.movies.data.enums.GenderImage;


import java.util.ArrayList;

public class CrewsDialogAdapter extends RecyclerView.Adapter<CrewsDialogAdapter.CrewsViewHolder> {
    private Context context;
    private ArrayList<Crew> list;

    public CrewsDialogAdapter(Context context, ArrayList<Crew> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CrewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrewsViewHolder(LayoutInflater.from(context).inflate(R.layout.crews_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrewsDialogAdapter.CrewsViewHolder holder, int position) {
        final Crew crew = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";

        if (crew.getProfile_path() != null){
            url += crew.getProfile_path();

            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.female)
                    .centerCrop()
                    .into(holder.imageView);
        }else {
            int profilePhoto = R.drawable.male;

            if (crew.getGender() == GenderImage.MALE.getGenderImage()){
                profilePhoto = R.drawable.male;
            }else if (crew.getGender() == GenderImage.FEMALE.getGenderImage()){
                profilePhoto = R.drawable.female;
            }

            holder.imageView.setImageResource(profilePhoto);
        }


        holder.crewName.setText(crew.getName());
        holder.crewDepartment.setText(crew.getDepartment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CrewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView crewName, crewDepartment;

        public CrewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_crew);
            crewName = itemView.findViewById(R.id.crew_text_name);
            crewDepartment = itemView.findViewById(R.id.crew_department);
        }
    }
}
