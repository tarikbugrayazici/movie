package com.example.movies.ui.detailtabs.view;

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
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Recommended;
import com.example.movies.data.entity.RecommendedMovie;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.RecommendedAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedFragment extends Fragment {
    private RecommendedAdapter adapter;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            setRecyclerView();
            initScrollListener();
            fetchRecommendedMovie(id);
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
        fetchRecommendedMovie(id);
    }

    private void fetchRecommendedMovie(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<BaseEntity> call = retrofit.getRecommendedMovies(id,
                "788a71cfbb2953df3cc3b1e7531ef259", "en-US", pagination);
        call.enqueue(new Callback<BaseEntity>() {
            @Override
            public void onResponse(Call<BaseEntity> call, Response<BaseEntity> response) {
                setData(response.body().getResults());
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
            public void onFailure(Call<BaseEntity> call, Throwable t) {
                System.out.println("hataAaAa" + t);
            }
        });
    }

    private void setLoadingCase() {
        if (isLoadingShowed) {
            movies.remove(movies.size() - 1);
            adapter.notifyDataSetChanged();
            isLoadingShowed = false;
        }
    }

    private void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecommendedAdapter(getContext(), movies);
        recyclerView.setAdapter(adapter);

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

    public static RecommendedFragment newInstance(int id) {
        RecommendedFragment fragment = new RecommendedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

}
