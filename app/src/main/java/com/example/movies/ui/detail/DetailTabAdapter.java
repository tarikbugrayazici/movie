package com.example.movies.ui.detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.movies.ui.detailtabs.view.ActorsFragment;
import com.example.movies.ui.detailtabs.view.InfoFragment;
import com.example.movies.ui.detailtabs.view.RecommendedFragment;
import com.example.movies.ui.detailtabs.view.SimilarFragment;

public class DetailTabAdapter extends FragmentPagerAdapter {
    private Context context;
    private int id;

    public DetailTabAdapter(FragmentManager fm, Context context, int id) {
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
                return  ActorsFragment.newInstance(id);
            case 2:
                return  SimilarFragment.Companion.newInstance(id);
            case 3:
                return  RecommendedFragment.Companion.newInstance(id);
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
                String bilgiler  = "Bilgiler";
                bilgiler = bilgiler.substring(0,1).toUpperCase() + bilgiler.substring(1).toLowerCase();
                return bilgiler;
            case 1:
                String oyuncular  = "Oyuncular";
                oyuncular = oyuncular.substring(0,1).toUpperCase() + oyuncular.substring(1).toLowerCase();
                return oyuncular;
            case 2:
                String benzeri  = "Benzeri";
                benzeri = benzeri.substring(0,1).toUpperCase() + benzeri.substring(1).toLowerCase();
                return benzeri;
            case 3:
                String ilgili  = "İlgili";
                ilgili = ilgili.substring(0,1).toUpperCase() + ilgili.substring(1).toLowerCase();
                return ilgili;
        }
        return null;
    }
}
