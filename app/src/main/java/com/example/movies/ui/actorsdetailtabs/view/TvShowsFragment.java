package com.example.movies.ui.actorsdetailtabs.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.R;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowsFragment extends Fragment {
    private int id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null){
            id= getArguments().getInt("id");
            fetchTv(id);

        }
    }
    private void fetchTv(int id){
        API retrofit = RetroFitService.getRetrofit().create(API.class);
       Call<BaseEntity> call = retrofit.getActorsTv(id,
                "788a71cfbb2953df3cc3b1e7531ef259", "en-US");
       call.enqueue(new Callback<BaseEntity>() {
           @Override
           public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
               response.body().getResults();
           }

           @Override
           public void onFailure(Call<BaseEntity> call, Throwable t) {

           }
       });
    }

    public static TvShowsFragment newInstance(int id) {
        TvShowsFragment tvShowsFragment = new TvShowsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        tvShowsFragment.setArguments(bundle);
        return tvShowsFragment;
    }
}
