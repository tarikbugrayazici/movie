package com.example.movies.ui.detailtabs.view;

import android.os.Bundle;

import com.example.movies.core.base.BasePageableFragment;
import com.example.movies.core.base.BaseSimilarPageableFragment;


public class RecommendedFragment extends BasePageableFragment {

    @Override
    public void fetchRequest() {
        service.fetchRecommendedMovie(id, this, pagination);
    }

    public static RecommendedFragment newInstance(int id) {
        RecommendedFragment fragment = new RecommendedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }


}
