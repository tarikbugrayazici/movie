package com.example.movies.ui.actorsdetailtabs.view;

import android.os.Bundle;

import com.example.movies.core.base.BaseActorsShowFragment;

public class TvShowsFragment extends BaseActorsShowFragment {

    @Override
    public void fetchActorsShows() {
        service.fetchActorsTv(id, this);
    }

    public static TvShowsFragment newInstance(int id) {
        TvShowsFragment tvShowsFragment = new TvShowsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        tvShowsFragment.setArguments(bundle);
        return tvShowsFragment;
    }


}
