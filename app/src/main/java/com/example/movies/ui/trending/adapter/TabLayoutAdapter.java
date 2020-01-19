package com.example.movies.ui.trending.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.movies.ui.trending.view.TabAllFragment;
import com.example.movies.ui.trending.view.TabMovieFragment;
import com.example.movies.ui.trending.view.TabPersonFragment;
import com.example.movies.ui.trending.view.TabTvFragment;


public class TabLayoutAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabLayoutAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabAllFragment();
            case 1:
                return new TabMovieFragment();
            case 2:
                return new TabTvFragment();
            case 3:
                return new TabPersonFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Movie";
            case 2:
                return "Tv";
            case 3:
                return "Person";
        }
        return null;
    }
}
