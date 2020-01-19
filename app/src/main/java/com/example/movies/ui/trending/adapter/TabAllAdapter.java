package com.example.movies.ui.trending.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Trending;
import com.example.movies.ui.detail.DetailActivity;

import java.util.ArrayList;

public class TabAllAdapter extends RecyclerView.Adapter<TabAllAdapter.TabAllAdapterHolder> {
    private Context context;
    private ArrayList<Movie> list;

    public TabAllAdapter(Context context, ArrayList<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TabAllAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabAllAdapterHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TabAllAdapterHolder holder, int position) {
        final Movie movie = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide.with(context).load(url+movie.getPoster_path())
                .centerCrop().into(holder.tabİmage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("movie", movie);
                v.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TabAllAdapterHolder extends RecyclerView.ViewHolder {
        ImageView tabİmage;
        public TabAllAdapterHolder(View itemView) {
            super(itemView);
            tabİmage = (ImageView) itemView.findViewById(R.id.img_view);

        }
    }
}
