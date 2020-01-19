package com.example.movies.ui.detailtabs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.ui.actorsDetail.ActorsDetailActivity;
import com.example.movies.R;
import com.example.movies.data.entity.Cast;


import java.util.ArrayList;

public class ActorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Cast> list;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public ActorsAdapter(Context context, ArrayList<Cast> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oyuncular_item, parent, false);
            return new ActorsViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ActorsViewHolder) {

            final Cast cast = list.get(position);
            String url = "https://image.tmdb.org/t/p/w500";
            if (cast.getGender() == 1) {
                Glide.with(context)
                        .load(url + cast.getProfile_path())
                        .placeholder(R.drawable.female)
                        .into(((ActorsViewHolder) holder).imageView);
                ((ActorsViewHolder) holder).textView.setText(cast.getName());
                ((ActorsViewHolder) holder).textView2.setText(cast.getCharacter());
            } else if (cast.getGender() == 2) {
                Glide.with(context)
                        .load(url + cast.getProfile_path())
                        .placeholder(R.drawable.male)
                        .into(((ActorsViewHolder) holder).imageView);
                ((ActorsViewHolder) holder).textView.setText(cast.getName());
                ((ActorsViewHolder) holder).textView2.setText(cast.getCharacter());
            } else {
                Glide.with(context)
                        .load(url + cast.getProfile_path())
                        .placeholder(R.drawable.male)
                        .into(((ActorsViewHolder) holder).imageView);
                ((ActorsViewHolder) holder).textView.setText(cast.getName());
                ((ActorsViewHolder) holder).textView2.setText(cast.getCharacter());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ActorsDetailActivity.class);
                    i.putExtra("actor",cast.getId());
                    v.getContext().startActivity(i);
                }
            });

        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public interface FragmentMovieListener {
        void onInputSent(CharSequence input);
    }

    public class ActorsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView, textView2, textViewNumber;

        public ActorsViewHolder(View itemView) {
            super(itemView);
            textViewNumber = itemView.findViewById(R.id.number_of_actors);
            imageView = itemView.findViewById(R.id.img_cast);
            textView = itemView.findViewById(R.id.oyuncu_text_name);
            textView2 = itemView.findViewById(R.id.oyuncu_text_movie_name);

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
        //ProgressBar would be displayed

    }
}
