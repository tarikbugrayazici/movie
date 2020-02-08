package com.example.movies.ui.actorsdetailtabs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.ActorsPhotos;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                .into(holder.photo);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.photo)
        ImageView photo;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
