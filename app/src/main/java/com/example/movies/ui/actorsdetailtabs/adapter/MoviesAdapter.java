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
import com.example.movies.data.entity.CastDetail;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<CastDetail> list;
    private int layout;

    private static int GRID = 0;
    private static int LINEAR = 1;

    public MoviesAdapter(Context context, ArrayList<CastDetail> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == GRID) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_cardview, viewGroup, false);
            return new GridLayoutViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_cardview_linear, viewGroup, false);
            return new LinearLayoutViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof GridLayoutViewHolder) {
            final CastDetail castDetail = list.get(position);
            String url = "https://image.tmdb.org/t/p/w500";
            Glide
                    .with(context)
                    .load(url + castDetail.getPoster_path())
                    .centerCrop()
                    .into(((GridLayoutViewHolder) viewHolder).imgViewCardView);
        } else if (viewHolder instanceof LinearLayoutViewHolder) {
            final CastDetail castDetail = list.get(position);
            String url = "https://image.tmdb.org/t/p/w500";
            Glide
                    .with(context)
                    .load(url + castDetail.getPoster_path())
                    .centerCrop()
                    .into(((LinearLayoutViewHolder) viewHolder).imgViewCardViewLinear);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.layout;
    }

    public class LinearLayoutViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_view_card_view_linear)
        ImageView imgViewCardViewLinear;

        public LinearLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class GridLayoutViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_view_card_view)
        ImageView imgViewCardView;

        public GridLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
