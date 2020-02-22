package com.example.movies.ui.trending.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.Person;
import com.example.movies.data.entity.Result;
import com.example.movies.data.entity.TabPersonEntity;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.trending.adapter.TabPersonAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;


public class TabPersonFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TabPersonAdapter adapter;
    private ArrayList<Person> people = new ArrayList<>();
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

        setRecyclerView();
        initScrollListener();
        fetchPerson();
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
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == people.size() - 1) {
                        loadMore();
                        isLoadingShowed = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        pagination++;
        people.add(null);
        adapter.notifyDataSetChanged();
        fetchPerson();
    }

    private void fetchPerson() {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<TabPersonEntity> tabPersonEntityResult = result;
                if (tabPersonEntityResult.getData() != null ||
                        (tabPersonEntityResult.getData() != null ? tabPersonEntityResult.getData().getResults() : null) != null
                                && (pagination == 1 || sizeOfPage == tabPersonEntityResult.getData().getResults().size())) {
                    setData(tabPersonEntityResult.getData().getResults());
                } else {
                    setLoadingCase();
                }
            }
        };
        service.fetchPerson(serviceCallBack);
    }

    private void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TabPersonAdapter(getContext(), people);
        recyclerView.setAdapter(adapter);
    }

    private void setLoadingCase() {
        if (isLoadingShowed) {
            people.remove(people.size() - 1);
            adapter.notifyDataSetChanged();
            isLoadingShowed = false;
        }
    }

    private void addItemsToList(ArrayList<Person> list) {
        sizeOfPage = list.size();
        people.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void setData(ArrayList<Person> list) {
        setLoadingCase();
        addItemsToList(list);
    }

}
