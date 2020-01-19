package com.example.movies.ui.search.view;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.R;
import com.example.movies.ui.search.adapter.TabLayoutAdapterSearch;


/**
 * Created by Tarik on 16.11.2019.
 */

public class SearchFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    tabLayout = (TabLayout)view.findViewById(R.id.tab_layout_search);
    viewPager = (ViewPager)view.findViewById(R.id.search_view_pager);
    TabLayoutAdapterSearch tabLayoutAdapterSearch = new TabLayoutAdapterSearch(getActivity()
            .getSupportFragmentManager(),getContext());
    viewPager.setAdapter(tabLayoutAdapterSearch);
    tabLayout.setupWithViewPager(viewPager);
    }

}



