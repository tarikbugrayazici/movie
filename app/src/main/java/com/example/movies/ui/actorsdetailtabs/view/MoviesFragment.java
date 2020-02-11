package com.example.movies.ui.actorsdetailtabs.view;

import android.os.Bundle;

import com.example.movies.core.base.BaseActorsShowFragment;

public class MoviesFragment extends BaseActorsShowFragment {

    @Override
    public void fetchActorsShows() {
        service.fetchActorsMovie(id, this);
    }

    public static MoviesFragment newInstance(int id) {
        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        moviesFragment.setArguments(bundle);
        return moviesFragment;
    }


}
