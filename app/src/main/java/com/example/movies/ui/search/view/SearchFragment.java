package com.example.movies.ui.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.ui.search.adapter.TabLayoutAdapterSearch;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * Created by Tarik on 16.11.2019.
 */

public class SearchFragment extends BaseFragment {

    @BindView(R.id.tab_layout_search)
    TabLayout tabLayoutSearch;
    @BindView(R.id.search_view_pager)
    ViewPager searchViewPager;

    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.search_fragment_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TabLayoutAdapterSearch tabLayoutAdapterSearch = new TabLayoutAdapterSearch(getActivity()
                .getSupportFragmentManager(), getContext());
        searchViewPager.setAdapter(tabLayoutAdapterSearch);
        tabLayoutSearch.setupWithViewPager(searchViewPager);
    }
}



