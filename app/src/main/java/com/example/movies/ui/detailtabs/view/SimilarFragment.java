package com.example.movies.ui.detailtabs.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.SimilarMovie;
import com.example.movies.data.entity.SimilarMovies;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.SimilarAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimilarFragment extends Fragment {
    private SimilarAdapter adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private ArrayList<Movie> movies = new ArrayList<>();
    private boolean isLoadingShowed = false;
    private int pagination = 1;
    private int sizeOfPage = 0;
    private int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            setRecyclerView();
            initScrollListener();
            fetchMovies(id);

        }
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoadingShowed) {
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == movies.size() - 1) {
                        Toast.makeText(getActivity(), "listenin sonu", Toast.LENGTH_LONG).show();
                        loadMore();
                    }
                }
            }
        });
    }
    private void loadMore(){
        pagination++;
        movies.add(null);
        adapter.notifyDataSetChanged();
        fetchMovies(id);
    }

    private void fetchMovies(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<BaseEntity> call = retrofit.getSimilarMovies(id,
                "788a71cfbb2953df3cc3b1e7531ef259", "en-US", pagination);
        call.enqueue(new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                setData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<BaseEntity> call, Throwable t) {

            }
        });
    }

    private void setData(ArrayList<Movie> list) {
        movies.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SimilarAdapter(getContext(), movies);
        recyclerView.setAdapter(adapter);
    }

    public static SimilarFragment newInstance(int id) {
        SimilarFragment fragment = new SimilarFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;

    }
}


