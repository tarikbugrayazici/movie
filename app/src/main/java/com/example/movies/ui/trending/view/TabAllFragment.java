package com.example.movies.ui.trending.view;

import com.example.movies.core.base.BaseListFragment;

public class TabAllFragment extends BaseListFragment {

    @Override
    public void fetchRequest() {
        service.fetchTabAllFragment(this);
    }
}
