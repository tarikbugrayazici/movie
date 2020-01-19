package com.example.movies.ui.trending.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.movies.R;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.service.API;
import com.example.movies.data.entity.BaseEntityTrending;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.data.entity.Trending;
import com.example.movies.ui.trending.adapter.TabAllAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabAllFragment extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fetchTabAllFragment();
    }

    public void fetchTabAllFragment() {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<BaseEntity> call = retrofit.getTrendAll("788a71cfbb2953df3cc3b1e7531ef259");
        call.enqueue(new Callback<BaseEntity>() {
            @Override
            public void onResponse(@NonNull Call<BaseEntity> call, @NonNull Response<BaseEntity> response) {
                response.body().getResults();
                setRecyclerView(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<BaseEntity> call, @NonNull Throwable t) {
                System.out.println("hata");

            }
        });
    }

    private void setRecyclerView(ArrayList<Movie> list) {
        ArrayList<Movie> trendings = new ArrayList<>();
        trendings.addAll(list);
        TabAllAdapter adapter = new TabAllAdapter(getContext(), trendings);
        recyclerView.setAdapter(adapter);
    }
}
