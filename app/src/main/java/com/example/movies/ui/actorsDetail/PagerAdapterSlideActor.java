package com.example.movies.ui.actorsDetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.Backdrops;
import com.example.movies.data.entity.Media;

import java.util.ArrayList;


public class PagerAdapterSlideActor extends PagerAdapter {
    private Context context;
    private ArrayList<Media> list;

    public PagerAdapterSlideActor(Context context, ArrayList<Media> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Media media = list.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        String url = "https://image.tmdb.org/t/p/w500";
        String imgUrl = url + media.getPoster_path();
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.view_pager_item, container, false);
        container.addView(viewGroup);
        ImageView imageView = viewGroup.findViewById(R.id.image);
        Glide.with(context).load(imgUrl).centerCrop().into(imageView);
        return viewGroup;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
