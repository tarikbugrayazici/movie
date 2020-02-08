package com.example.movies.ui.detailtabs.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.core.navigation.Navigation;
import com.example.movies.data.entity.Cast;
import com.example.movies.data.enums.GenderImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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

            if (cast.getProfile_path() != null) {
                url += cast.getProfile_path();

                Glide.with(context)
                        .load(url)
                        .centerCrop()
                        .into(((ActorsViewHolder) holder).imgCast);
            } else {
                int profilePhoto = R.drawable.male;

                if (cast.getGender() == GenderImage.FEMALE.getGenderImage()) {
                    profilePhoto = R.drawable.female;
                }

                ((ActorsViewHolder) holder).imgCast.setImageResource(profilePhoto);
            }

            ((ActorsViewHolder) holder).oyuncuTextName.setText(cast.getName());
            ((ActorsViewHolder) holder).oyuncuTextMovieName.setText(cast.getCharacter());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.startActorsDetailActivity(context, cast.getId());
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
        @BindView(R.id.img_cast)
        CircleImageView imgCast;
        @BindView(R.id.oyuncu_text_name)
        TextView oyuncuTextName;
        @BindView(R.id.oyuncu_text_movie_name)
        TextView oyuncuTextMovieName;

        public ActorsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
