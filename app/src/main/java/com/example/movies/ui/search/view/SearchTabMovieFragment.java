package com.example.movies.ui.search.view;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.movies.R;
import com.example.movies.data.entity.SearchMovie;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.data.entity.SearchMovieBaseEntity;
import com.example.movies.ui.search.adapter.SearchTabMovieAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchTabMovieFragment extends Fragment {
    RecyclerView recyclerView;
    EditText editText;
    BottomNavigationView bottomNavigationView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_tab_movie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.search_movie_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        editText = (EditText) view.findViewById(R.id.edit_movie);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 3) {
                    String text = s.toString();
                    fetchNamesMovie(text);
                }


            }
        });
    }

    private void fetchNamesMovie(String text) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<SearchMovieBaseEntity> call = retrofit.getSearchMovie("788a71cfbb2953df3cc3b1e7531ef259",
                "en-US", text, 1, false);
        call.enqueue(new Callback<SearchMovieBaseEntity>() {
            @Override
            public void onResponse(@NonNull Call<SearchMovieBaseEntity> call, @NonNull Response<SearchMovieBaseEntity> response) {
                response.body().getResults();
                setRecyclerView(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<SearchMovieBaseEntity> call, @NonNull Throwable t) {
                System.out.println("hata11");
            }
        });
    }

    private void setRecyclerView(ArrayList<SearchMovie> list) {
        ArrayList<SearchMovie> searchMovies = new ArrayList<>();
        searchMovies.addAll(list);
        SearchTabMovieAdapter adapter = new SearchTabMovieAdapter(getContext(), searchMovies);
        recyclerView.setAdapter(adapter);
    }
}
