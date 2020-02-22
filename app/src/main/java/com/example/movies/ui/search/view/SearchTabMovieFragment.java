package com.example.movies.ui.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.search.adapter.SearchTabMovieAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;


public class SearchTabMovieFragment extends BaseFragment {
    RetroFitService service = new RetroFitService();
    String text;
    @BindView(R.id.edit_movie)
    EditText editMovie;
    @BindView(R.id.search_movie_recycler_view)
    RecyclerView searchMovieRecyclerView;

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_search_tab_movie;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchMovieRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        editMovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 3) {
                    text = s.toString();
                    fetchNamesMovie(text);
                }
            }
        });
    }

    private void fetchNamesMovie(String text) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<BaseEntity> baseEntityResult = result;
                if (baseEntityResult.getData() != null) {
                    setRecyclerView(baseEntityResult.getData().getResults());
                } else {
                    Toast.makeText(getActivity(), baseEntityResult.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchNamesMovie(serviceCallBack, text);
    }

    private void setRecyclerView(ArrayList<Movie> list) {
        ArrayList<Movie> searchMovies = new ArrayList<>();
        searchMovies.addAll(list);
        SearchTabMovieAdapter adapter = new SearchTabMovieAdapter(getContext(), searchMovies);
        searchMovieRecyclerView.setAdapter(adapter);
    }
}
