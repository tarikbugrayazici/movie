package com.example.movies.ui.detailtabs.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.data.entity.Cast;
import com.example.movies.data.entity.MovieCrew;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.detailtabs.adapter.ActorsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorsFragment extends Fragment implements Dialog.DialogListener {
    private ActorsAdapter adapter;
    private ArrayList<Cast> casts = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TextView textViewNumberOfActors, textViewSort;
    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.actors, container, false);
    }

    @Override
    public void onFinishDialog(int inputText) {
        if (inputText == 0){
            adapter.notifyDataSetChanged();
        }
        else if (inputText == 1) {
            Collections.sort(casts, new Comparator<Cast>() {
                @Override
                public int compare(Cast o1, Cast o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            adapter.notifyDataSetChanged();
        } else if (inputText == 2)
            Collections.sort(casts,  new Comparator<Cast>() {
                @Override
                public int compare(Cast o1, Cast o2) {
                    return o1.getCharacter().compareTo(o2.getCharacter());
                }
            });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view_person);
        textViewNumberOfActors = view.findViewById(R.id.number_of_actors);
        textViewSort = view.findViewById(R.id.sort);
        textViewSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            setRecyclerView();
            fetchMovies(id);
        }
    }

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.setTargetFragment(ActorsFragment.this, 1);
        dialog.show(getFragmentManager(), "dialog");
    }

    private void fetchMovies(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<MovieCrew> call = retrofit.getMovieCrew(id, "788a71cfbb2953df3cc3b1e7531ef259");
        call.enqueue(new Callback<MovieCrew>() {
            @Override
            public void onResponse(@NonNull Call<MovieCrew> call, @NonNull Response<MovieCrew> response) {
                setData(response.body().getCast());

            }

            @Override
            public void onFailure(@NonNull Call<MovieCrew> call, @NonNull Throwable t) {
                System.out.println("hataaaaaaaaaaaa" + t);
            }
        });

    }

    private void setRecyclerView() {
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ActorsAdapter(getContext(), casts);
        recyclerView.setAdapter(adapter);
    }


    private void setData(ArrayList<Cast> list) {
        casts.addAll(list);
        textViewNumberOfActors.setText(casts.size() + " kişi");
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
