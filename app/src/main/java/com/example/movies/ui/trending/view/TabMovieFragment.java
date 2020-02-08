package com.example.movies.ui.trending.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.trending.adapter.TabMovieAdapter;

import java.util.ArrayList;

import butterknife.BindView;


public class TabMovieFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private TabMovieAdapter adapter;
    private ArrayList<Movie> trendings = new ArrayList<>();
    private GridLayoutManager layoutManager;
    private boolean isLoadingShowed = false;
    private int pagination = 1;
    private int sizeOfPage = 0;
    RetroFitService service = new RetroFitService();

    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.fragment_layout;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        setRecyclerView();
        initScrollListener();
        fetchTabMovie();
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
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == trendings.size() - 1) {
                        loadMore();
                        isLoadingShowed = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        pagination++;
        trendings.add(null);
        adapter.notifyDataSetChanged();
        fetchTabMovie();
    }

    private void fetchTabMovie() {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
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
        };
        service.fetchTabMovie(serviceCallBack);
    }

    private void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TabMovieAdapter(getContext(), trendings);
        recyclerView.setAdapter(adapter);
    }

    private void setLoadingCase() {
        if (isLoadingShowed) {
            trendings.remove(trendings.size() - 1);
            adapter.notifyDataSetChanged();
            isLoadingShowed = false;
        }
    }

    private void addItemsToList(ArrayList<Movie> list) {
        sizeOfPage = list.size();
        trendings.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void setData(ArrayList<Movie> list) {
        setLoadingCase();
        addItemsToList(list);
    }

}
