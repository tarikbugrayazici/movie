package com.example.movies.ui.detailtabs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.core.navigation.Navigation;
import com.example.movies.data.entity.Movie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimilarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Movie> list;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public SimilarAdapter(Context context, ArrayList<Movie> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_cardview, parent, false);
            return new SimilarViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SimilarViewHolder) {
            final Movie similarMovie = list.get(position);
            String url = "https://image.tmdb.org/t/p/w500";
            Glide.with(context)
                    .load(url + similarMovie.getPoster_path())
                    .into(((SimilarViewHolder) holder).imgViewCardView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.startDetailActivity(context, similarMovie.getId());
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class SimilarViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_view_card_view)
        ImageView imgViewCardView;

        public SimilarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {

    }
}
