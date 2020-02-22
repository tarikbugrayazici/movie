package com.example.movies.core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.data.entity.ActorsMoviesEntity;
import com.example.movies.data.entity.CastDetail;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.actorsdetailtabs.adapter.MoviesAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public abstract class BaseActorsShowFragment extends BaseFragment implements RetroFitService.ResultCallBack {
    @BindView(R.id.recycler_view_actors)
    RecyclerView recyclerViewActors;
    public MoviesAdapter adapter;
    public ArrayList<CastDetail> castDetails = new ArrayList<>();
    public int id;
    public boolean isGridLayoutManager = true;
    public RetroFitService service = new RetroFitService();

    public abstract void fetchActorsShows();


    @Override
    public int getFragmentLayoutId() {
        return R.layout.actors_movies;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewActors = view.findViewById(R.id.recycler_view_actors);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            fetchActorsShows();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGridLayoutManager = !isGridLayoutManager;
                setRecyclerView(isGridLayoutManager);
            }
        });
    }


    private void setData(ArrayList<CastDetail> list) {
        castDetails.addAll(list);
        setRecyclerView(isGridLayoutManager);
    }

    public void setRecyclerView(boolean isGridLayoutManager) {
        if (isGridLayoutManager) {
            recyclerViewActors.setLayoutManager(new GridLayoutManager(getContext(), 3));
        } else {
            recyclerViewActors.setLayoutManager(new LinearLayoutManager(getContext()));
        }


        int layoutType = 0;
        if (!isGridLayoutManager) {
            layoutType = 1;
        }
        adapter = new MoviesAdapter(getContext(), castDetails, layoutType);
        recyclerViewActors.setAdapter(adapter);
    }

    @Override
    public void getResult(Result result) {
        Result<ActorsMoviesEntity> actorsMoviesEntityResult = result;
        if (actorsMoviesEntityResult.getData() != null) {
            setData(actorsMoviesEntityResult.getData().getCast());
        } else {
            Toast.makeText(getActivity(), result.getErrorMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
