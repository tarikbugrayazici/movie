package com.example.movies.ui.detailtabs.view;

import android.os.Bundle;

import com.example.movies.core.base.BasePageableFragment;
import com.example.movies.core.base.BaseSimilarPageableFragment;

public class SimilarFragment extends BasePageableFragment {
    @Override
    public void fetchRequest() {
        service.fetchSimilarMovies(id, this, pagination);
    }

    public static SimilarFragment newInstance(int id) {
        SimilarFragment fragment = new SimilarFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;

    }


}


