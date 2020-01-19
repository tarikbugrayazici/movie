package com.example.movies.ui.actorsdetailtabs.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.ActorsEntity;
import com.example.movies.data.entity.ActorsPhotos;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private Context context;
    private ArrayList<ActorsPhotos> list;

    public InfoAdapter(Context context, ArrayList<ActorsPhotos> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new InfoViewHolder(LayoutInflater.from(context).inflate(R.layout.actors_photo_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        final ActorsPhotos actorsPhotos = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide
                .with(context)
                .load(url + actorsPhotos.getFile_path())
                .centerCrop()
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo);
        }
    }
}
