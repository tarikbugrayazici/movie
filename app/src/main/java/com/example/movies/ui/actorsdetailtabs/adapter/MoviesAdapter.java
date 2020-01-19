package com.example.movies.ui.actorsdetailtabs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.CastDetail;
import com.example.movies.data.entity.Movie;
import com.example.movies.ui.actorsDetail.ActorsDetailActivity;
import com.example.movies.ui.detail.DetailActivity;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private Context context;
    private ArrayList<CastDetail> list;

    public MoviesAdapter(Context context, ArrayList<CastDetail> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MoviesViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        final CastDetail castDetail = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide
                .with(context)
                .load(url + castDetail.getPoster_path())
                .centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("id", castDetail.getId());
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_view_card_view);
        }
    }
}
