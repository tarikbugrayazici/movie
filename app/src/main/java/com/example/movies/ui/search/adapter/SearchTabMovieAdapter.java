package com.example.movies.ui.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.core.navigation.Navigation;
import com.example.movies.data.entity.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTabMovieAdapter extends RecyclerView.Adapter<SearchTabMovieAdapter.SearchTabMovieAdapterHolder> {

    private Context context;
    private ArrayList<Movie> list;

    public SearchTabMovieAdapter(Context context, ArrayList<Movie> list) {
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
        final Movie movie = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide.with(context).load(url + movie.getPoster_path())
                .centerCrop().into(holder.imgView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.startDetailActivity(context, movie.getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchTabMovieAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_view)
        ImageView imgView;

        public SearchTabMovieAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
