package com.example.movies.core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.movies.R;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.RecommendedAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public abstract class BaseSimilarPageableFragment extends BaseFragment implements RetroFitService.ResultCallBack {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    public RecommendedAdapter adapter;
    public GridLayoutManager layoutManager;
    public ArrayList<Movie> movies = new ArrayList<>();
    public boolean isLoadingShowed = false;
    public int pagination = 1;
    public int sizeOfPage = 0;
    public int id;
    public RetroFitService service = new RetroFitService();

    public abstract void fetchRequest();

    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.fragment_layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            setRecyclerView();
            initScrollListener();
            fetchRequest();
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
        fetchRequest();
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

    @Override
    public void getResult(Result result) {
        Result<BaseEntity> baseEntityResult = result;
        if (baseEntityResult.getData() != null ||
                (baseEntityResult.getData() != null ? baseEntityResult.getData().getResults() : null) != null
                        && (pagination == 1 || sizeOfPage == baseEntityResult.getData().getResults().size())) {
            setData(baseEntityResult.getData().getResults());
        } else {
            setLoadingCase();
        }

    }
}

