package com.example.movies.ui.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.SearchMovie;

import java.util.ArrayList;

public class SearchTabMovieAdapter extends RecyclerView.Adapter<SearchTabMovieAdapter.SearchTabMovieAdapterHolder>  {
    private Context context;
    private ArrayList<SearchMovie> list;

    public SearchTabMovieAdapter(Context context, ArrayList<SearchMovie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchTabMovieAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchTabMovieAdapterHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchTabMovieAdapterHolder holder, int position) {
        final SearchMovie searchMovie = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide.with(context).load(url+searchMovie.getPoster_path())
                .centerCrop().into(holder.searchTabMovie);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchTabMovieAdapterHolder extends RecyclerView.ViewHolder {
        ImageView searchTabMovie;
        public SearchTabMovieAdapterHolder(View itemView) {
            super(itemView);
            searchTabMovie = (ImageView) itemView.findViewById(R.id.img_view);

        }
    }
}
