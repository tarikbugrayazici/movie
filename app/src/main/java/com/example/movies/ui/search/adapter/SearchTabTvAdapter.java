package com.example.movies.ui.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.SearchTv;

import java.util.ArrayList;

public class SearchTabTvAdapter extends RecyclerView.Adapter<SearchTabTvAdapter.SearchTabTvAdapterHolder>  {
    private Context context;
    private ArrayList<SearchTv> list;
    private ArrayList<SearchTv> listF;

    public SearchTabTvAdapter(Context context, ArrayList<SearchTv> list) {
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
        final SearchTv searchTv = list.get(position);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide.with(context).load(url+searchTv.getPoster_path())
                .centerCrop().into(holder.searchTabTv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchTabTvAdapterHolder extends RecyclerView.ViewHolder {
        ImageView searchTabTv;
        public SearchTabTvAdapterHolder(View itemView) {
            super(itemView);
            searchTabTv = (ImageView) itemView.findViewById(R.id.img_view);

        }
    }
}
