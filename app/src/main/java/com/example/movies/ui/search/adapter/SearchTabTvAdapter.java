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
import com.example.movies.core.util.Constants;
import com.example.movies.data.entity.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchTabTvAdapter extends RecyclerView.Adapter<SearchTabTvAdapter.SearchTabTvAdapterHolder> {

    private Context context;
    private ArrayList<Movie> list;


    public SearchTabTvAdapter(Context context, ArrayList<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchTabTvAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchTabTvAdapterHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SearchTabTvAdapterHolder holder, int position) {
        final Movie movie = list.get(position);
        Glide.with(context).load(Constants.IMAGE_BASE_PATH + movie.getPoster_path())
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

    public class SearchTabTvAdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_view)
        ImageView imgView;

        public SearchTabTvAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
