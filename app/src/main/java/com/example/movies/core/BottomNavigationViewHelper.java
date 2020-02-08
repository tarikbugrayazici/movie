package com.example.movies.core;

/**
 * Created by Tarik on 16.11.2019.
 */

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;


import com.example.movies.ui.movie.view.MovieFragment;
import com.example.movies.ui.search.view.SearchFragment;
import com.example.movies.ui.trending.view.TrendingFragment;
import com.example.movies.ui.tv.view.TvFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class BottomNavigationViewHelper {

    public static ArrayList<Fragment> getBottomNavigationItems(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MovieFragment());
        fragments.add(new TvFragment());
        fragments.add(new SearchFragment());
        fragments.add(new TrendingFragment());
        return fragments;
    }
}
