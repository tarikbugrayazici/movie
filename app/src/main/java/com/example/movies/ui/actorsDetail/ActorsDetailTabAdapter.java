package com.example.movies.ui.actorsDetail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.movies.ui.actorsdetailtabs.view.InfoFragment;
import com.example.movies.ui.actorsdetailtabs.view.MoviesFragment;
import com.example.movies.ui.actorsdetailtabs.view.TvShowsFragment;

public class ActorsDetailTabAdapter extends FragmentPagerAdapter {
    private Context context;
    private int id;

    public ActorsDetailTabAdapter(FragmentManager fm, Context context, int id) {
        super(fm);
        this.context = context;
        this.id = id;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InfoFragment.newInstance(id);
            case 1:
                return MoviesFragment.Companion.newInstance(id);
            case 2:
                return  TvShowsFragment.Companion.newInstance(id);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                String bilgiler = "Bilgiler";
                bilgiler = bilgiler.substring(0, 1).toUpperCase() + bilgiler.substring(1).toLowerCase();
                return bilgiler;
            case 1:
                String filmler = "Filmler";
                filmler = filmler.substring(0, 1).toUpperCase() + filmler.substring(1).toLowerCase();
                return filmler;
            case 2:
                String tv = "TV Programları";
                tv = tv.substring(0, 1).toUpperCase() + tv.substring(1).toLowerCase();
                return tv;
        }
        return null;
    }
}
