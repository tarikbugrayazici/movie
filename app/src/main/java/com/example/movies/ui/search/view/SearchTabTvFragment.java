package com.example.movies.ui.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.Movie;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.search.adapter.SearchTabTvAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SearchTabTvFragment extends BaseFragment {
    RetroFitService service = new RetroFitService();
    @BindView(R.id.edit_tv)
    EditText editTv;
    @BindView(R.id.search_tv_recycler_view)
    RecyclerView searchTvRecyclerView;

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_search_tab_tv;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchTvRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        editTv.addTextChangedListener(new TextWatcher() {
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
                    fetchTvNames(text);
                }
            }
        });
    }

    private void fetchTvNames(String text) {
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
        service.fetchTvNames(serviceCallBack, text);
    }

    private void setRecyclerView(ArrayList<Movie> list) {
        ArrayList<Movie> searchTvs = new ArrayList<>();
        searchTvs.addAll(list);
        SearchTabTvAdapter adapter = new SearchTabTvAdapter(getContext(), searchTvs);
        searchTvRecyclerView.setAdapter(adapter);
    }
}
