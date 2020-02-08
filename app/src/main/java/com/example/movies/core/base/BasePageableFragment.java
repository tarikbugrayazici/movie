package com.example.movies.core.base;

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
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.movie.adapter.MovieAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BasePageableFragment extends BaseListFragment implements RetroFitService.ResultCallBack {

    private boolean isLoadingShowed = false;
    public int pagination = 1;
    public int sizeOfPage = 0;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initScrollListener();
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
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == list.size() - 1) {
                        loadMore();
                        isLoadingShowed = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        pagination++;
        list.add(null);
        adapter.notifyDataSetChanged();
        fetchRequest();
    }

    public void setLoadingCase() {
        if (isLoadingShowed) {
            list.remove(list.size() - 1);
            adapter.notifyDataSetChanged();
            isLoadingShowed = false;
        }
    }

    public void addItemsToList(ArrayList<Movie> list) {
        sizeOfPage = list.size();
        super.addItemsToList(list);
    }

    public void setData(ArrayList<Movie> list) {
        setLoadingCase();
        super.setData(list);
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

