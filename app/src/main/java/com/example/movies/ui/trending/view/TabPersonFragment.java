package com.example.movies.ui.trending.view;

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
import com.example.movies.data.entity.TabPersonEntity;
import com.example.movies.data.service.API;
import com.example.movies.data.entity.Person;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.trending.adapter.TabPersonAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TabPersonFragment extends Fragment {
    private TabPersonAdapter adapter;
    private ArrayList<Person> people = new ArrayList<>();
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private boolean isLoadingShowed = false;
    private int pagination = 1;
    private int sizeOfPage = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_layout, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
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

    private void fetchPerson(){
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<TabPersonEntity> call = retrofit.getTrendPerson("788a71cfbb2953df3cc3b1e7531ef259");
        call.enqueue(new Callback<TabPersonEntity>() {
            @Override
            public void onResponse(@NonNull Call<TabPersonEntity> call, @NonNull Response<TabPersonEntity> response) {
                if (response.body() != null
                        || (response.body() != null ? response.body().getResults() : null) != null
                        && (pagination == 1 || sizeOfPage == response.body().getResults().size())) {
                    setData(response.body().getResults());
                }else {
                    setLoadingCase();
                    Toast.makeText(getActivity(), "End of the list", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TabPersonEntity> call, @NonNull Throwable t) {
                System.out.println("hataaaaaaaaaaaa");
            }
        });
    }
    private void setRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TabPersonAdapter(getContext(), people);
        recyclerView.setAdapter(adapter);
    }

    private void setLoadingCase(){
        if (isLoadingShowed){
            people.remove(people.size() - 1);
            adapter.notifyDataSetChanged();
            isLoadingShowed = false;
        }
    }

    private void addItemsToList(ArrayList<Person> list){
        sizeOfPage = list.size();
        people.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void setData(ArrayList<Person> list){
        setLoadingCase();
        addItemsToList(list);
    }
}
