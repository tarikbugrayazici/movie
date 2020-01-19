package com.example.movies.ui.search.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.movies.ui.search.view.SearchTabMovieFragment;
import com.example.movies.ui.search.view.SearchTabTvFragment;


public class TabLayoutAdapterSearch extends FragmentPagerAdapter {
    private Context context;
    public TabLayoutAdapterSearch(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SearchTabMovieFragment();
            case 1:
                return new SearchTabTvFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movie";
            case 1:
                return "Tv";
        }
        return null;
    }
}

