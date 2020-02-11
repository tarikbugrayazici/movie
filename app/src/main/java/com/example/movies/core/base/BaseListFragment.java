package com.example.movies.core.base;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.movies.R;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.movie.adapter.MovieAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public abstract class BaseListFragment extends BaseFragment implements RetroFitService.ResultCallBack {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public MovieAdapter adapter;
    public ArrayList<Movie> list = new ArrayList<>();
    public GridLayoutManager layoutManager;

    public RetroFitService service = new RetroFitService();

    public abstract void fetchRequest();

    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.fragment_layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
        fetchRequest();
    }

    public void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
    }

    public void addItemsToList(ArrayList<Movie> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public void setData(ArrayList<Movie> list) {
        addItemsToList(list);
    }



    @Override
    public void getResult(Result result) {
        Result<BaseEntity> baseEntityResult = result;
        if (baseEntityResult.getData() != null) {
            setData(baseEntityResult.getData().getResults());
        }
    }
}

