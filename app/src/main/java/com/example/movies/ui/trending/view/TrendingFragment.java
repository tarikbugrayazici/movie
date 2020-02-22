package com.example.movies.ui.trending.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.ui.trending.adapter.TabLayoutAdapter;

import butterknife.BindView;



/**
 * Created by Tarik on 16.11.2019.
 */

public class TrendingFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int getFragmentLayoutId() {
        return R.layout.trending_fragment_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getActivity()
                .getSupportFragmentManager(), getContext());
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}

