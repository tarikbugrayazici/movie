package com.example.movies.ui.trending.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.R;
import com.example.movies.ui.trending.adapter.TabLayoutAdapter;


/**
 * Created by Tarik on 16.11.2019.
 */

public class TrendingFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trending_fragment_layout,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout =  view.findViewById(R.id.tab_layout);
        viewPager =  view.findViewById(R.id.view_pager);
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getActivity()
                .getSupportFragmentManager(),getContext());
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}

