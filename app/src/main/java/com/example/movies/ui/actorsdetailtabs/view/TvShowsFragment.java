package com.example.movies.ui.actorsdetailtabs.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.ActorsMoviesEntity;
import com.example.movies.data.entity.BaseEntity;
import com.example.movies.data.entity.CastDetail;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.actorsdetailtabs.adapter.MoviesAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowsFragment extends BaseFragment {
    @BindView(R.id.recycler_view_actors)
    RecyclerView recyclerView;
    @BindView(R.id.textOfView)
    TextView view;
    private Unbinder unbinder;
    private GridLayoutManager layoutManager;
    private MoviesAdapter adapter;
    private ArrayList<CastDetail> castDetails = new ArrayList<>();
    private int id;
    private boolean clicked = false;
    private int layout = 0;
    RetroFitService service = new RetroFitService();


    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.actors_movies;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            fetchActorsTv(id);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = !clicked;
                setRecyclerview(clicked);
            }
        });
    }

    private void fetchActorsTv(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<ActorsMoviesEntity> actorsMoviesEntityResult = result;
                if (actorsMoviesEntityResult.getData() != null) {
                    setData(actorsMoviesEntityResult.getData().getCast());
                } else {
                    Toast.makeText(getActivity(), result.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchActorsTv(id, serviceCallBack);
    }

    private void setRecyclerview(boolean clicked) {
        if (clicked) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            layout = 0;
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            layout = 1;
        }
        adapter = new MoviesAdapter(getContext(), castDetails, layout);
        recyclerView.setAdapter(adapter);

    }

    private void setData(ArrayList<CastDetail> list) {
        castDetails.addAll(list);
        setRecyclerview(clicked);
    }

    public static TvShowsFragment newInstance(int id) {
        TvShowsFragment tvShowsFragment = new TvShowsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        tvShowsFragment.setArguments(bundle);
        return tvShowsFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
