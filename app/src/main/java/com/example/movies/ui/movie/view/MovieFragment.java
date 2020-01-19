package com.example.movies.ui.movie.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.data.service.API;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.movie.adapter.MovieAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tarik on 16.11.2019.
 */

public class MovieFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private MovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private GridLayoutManager layoutManager;
    private boolean isLoadingShowed = false;
    private int pagination = 1;
    private int sizeOfPage = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
        initScrollListener();
        fetchMovies();
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
                        System.out.println("movie sonn");
                        loadMore();
                        isLoadingShowed = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        pagination++;
        movies.add(null);
        adapter.notifyDataSetChanged();
        fetchMovies();
    }

    private void fetchMovies() {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<BaseEntity> call = retrofit.getMovie("788a71cfbb2953df3cc3b1e7531ef259",
                "en-US", "popularity.desc", false,
                false, pagination);
        call.enqueue(new Callback<BaseEntity>() {
            @Override
            public void onResponse(@NonNull Call<BaseEntity> call, @NonNull Response<BaseEntity> response) {
                if (response.body() != null
                        || (response.body() != null ? response.body().getResults() : null) != null
                        && (pagination == 1 || sizeOfPage == response.body().getResults().size())) {
                    setData(response.body().getResults());
                } else {
                    setLoadingCase();
                    Toast.makeText(getActivity(), "End of the list", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseEntity> call, @NonNull Throwable t) {
                System.out.println("hataaaaaaaaaaaa");
            }
        });

    }

    private void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(getContext(), movies);
        recyclerView.setAdapter(adapter);
    }

    private void setLoadingCase() {
        if (isLoadingShowed) {
            movies.remove(movies.size() - 1);
            adapter.notifyDataSetChanged();
            isLoadingShowed = false;
        }
    }

    private void addItemsToList(ArrayList<Movie> list) {
        sizeOfPage = list.size();
        movies.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void setData(ArrayList<Movie> list) {
        setLoadingCase();
        addItemsToList(list);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
