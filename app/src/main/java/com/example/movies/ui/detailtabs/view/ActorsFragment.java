package com.example.movies.ui.detailtabs.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.Cast;
import com.example.movies.data.entity.MovieCrew;
import com.example.movies.data.entity.Result;
import com.example.movies.data.enums.Sorting;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.ActorsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;

public class ActorsFragment extends BaseFragment implements Dialog.DialogListener {
    @BindView(R.id.number_of_actors)
    TextView numberOfActors;
    @BindView(R.id.sort)
    TextView sort;
    @BindView(R.id.recycler_view_person)
    RecyclerView recyclerViewPerson;
    private ActorsAdapter adapter;
    private ArrayList<Cast> casts = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int id;
    RetroFitService service = new RetroFitService();

    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.actors;
    }

    @Override
    public void onFinishDialog(int inputText) {
        ArrayList<Cast> list = new ArrayList<>(sortedList(inputText));
        adapter = new ActorsAdapter(getContext(), list);
        recyclerViewPerson.setAdapter(adapter);
    }

    private ArrayList<Cast> sortedList(int type) {
        ArrayList<Cast> castSorted = new ArrayList<>(casts);
        if (type == Sorting.BY_NAME.getValue()) {
            Collections.sort(castSorted, new Comparator<Cast>() {
                @Override
                public int compare(Cast o1, Cast o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

        } else if (type == Sorting.BY_CHARACTER.getValue()) {
            Collections.sort(castSorted, new Comparator<Cast>() {
                @Override
                public int compare(Cast o1, Cast o2) {
                    return o1.getCharacter().compareTo(o2.getCharacter());
                }
            });
        }

        return castSorted;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            setRecyclerView();
            fetchActorMovies(id);
        }
    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.setTargetFragment(ActorsFragment.this, 1);
        dialog.show(getFragmentManager(), "dialog");
    }

    private void fetchActorMovies(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<MovieCrew> movieCrewResult = result;
                if (movieCrewResult.getData() != null) {
                    setData(movieCrewResult.getData().getCast());
                } else {
                    Toast.makeText(getActivity(), movieCrewResult.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchActorMovies(id, serviceCallBack);
    }

    private void setRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewPerson.setLayoutManager(layoutManager);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("sorting", Context.MODE_PRIVATE);
        int sortType = sharedPreferences.getInt("key", Sorting.BY_RECOMMENDED.getValue());
        ArrayList<Cast> list = new ArrayList<>(sortedList(sortType));

        adapter = new ActorsAdapter(getContext(), list);
        recyclerViewPerson.setAdapter(adapter);
    }

    private void setData(ArrayList<Cast> list) {
        casts.addAll(list);
        numberOfActors.setText(casts.size() + " kişi");
        setRecyclerView();

    }

    public static ActorsFragment newInstance(int id) {
        ActorsFragment fragment = new ActorsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;

    }
}
