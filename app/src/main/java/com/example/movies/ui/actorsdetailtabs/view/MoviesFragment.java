package com.example.movies.ui.actorsdetailtabs.view;

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
import com.example.movies.data.entity.ActorsMoviesEntity;
import com.example.movies.data.entity.Cast;
import com.example.movies.data.entity.CastDetail;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.actorsdetailtabs.adapter.MoviesAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {
    private GridLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private ArrayList<CastDetail> castDetails = new ArrayList<>();
    private int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            fetchActorsMovie(id);
        }
    }

    private void fetchActorsMovie(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<ActorsMoviesEntity> call = retrofit.getActorsMovie(id,
                "788a71cfbb2953df3cc3b1e7531ef259", "en-US");
        call.enqueue(new Callback<ActorsMoviesEntity>() {
            @Override
            public void onResponse(Call<ActorsMoviesEntity> call, Response<ActorsMoviesEntity> response) {
                setData(response.body().getCast());
            }

            @Override
            public void onFailure(Call<ActorsMoviesEntity> call, Throwable t) {

            }
        });
    }

    private void setData(ArrayList<CastDetail> list) {
        castDetails.addAll(list);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoviesAdapter(getContext(), castDetails);
        recyclerView.setAdapter(adapter);
    }


    public static MoviesFragment newInstance(int id) {
        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        moviesFragment.setArguments(bundle);
        return moviesFragment;
    }
}
