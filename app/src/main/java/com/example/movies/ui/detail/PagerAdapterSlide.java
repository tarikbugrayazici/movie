package com.example.movies.ui.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.core.util.Constants;
import com.example.movies.data.entity.Backdrops;

import java.util.ArrayList;


public class PagerAdapterSlide extends PagerAdapter {
    private Context context;
    private ArrayList<Backdrops> list;

    public PagerAdapterSlide(Context context, ArrayList<Backdrops> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Backdrops backdrops = list.get(position);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewGroup viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.view_pager_item, container, false);
        container.addView(viewGroup);
        ImageView imageView = viewGroup.findViewById(R.id.image);
        Glide.with(context).load(Constants.IMAGE_BASE_PATH + backdrops.getFile_path()).centerCrop().into(imageView);
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
