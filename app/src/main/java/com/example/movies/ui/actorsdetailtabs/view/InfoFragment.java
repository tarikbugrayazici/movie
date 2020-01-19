package com.example.movies.ui.actorsdetailtabs.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.data.entity.ActorsEntity;
import com.example.movies.data.entity.ActorsPhotoEntity;
import com.example.movies.data.entity.ActorsPhotos;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.actorsdetailtabs.adapter.InfoAdapter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoFragment extends Fragment {
    private ArrayList<ActorsPhotos> actorsPhotos = new ArrayList<>();
    private InfoAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private String DATE_FORMAT = "yyyy-MM-dd";
    private TextView birthday, birthPlace, knownName, biography;
    private ArrayList<ActorsEntity> actorsEntities = new ArrayList<>();
    private int id;
    private boolean isCharacterCountOfOverviewOverflowed = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.actors_detail_info_items, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.actors_photo_recyclerview);
        birthday = view.findViewById(R.id.birthday);
        birthPlace = view.findViewById(R.id.birth_place);
        knownName = view.findViewById(R.id.known_name);
        biography = view.findViewById(R.id.biography);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            service(id);
            fetchPhotos(id);
        }
    }

    private void service(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<ActorsEntity> call = retrofit.getActorsDetail(id, "788a71cfbb2953df3cc3b1e7531ef259",
                "en=-US");
        call.enqueue(new Callback<ActorsEntity>() {
            @Override
            public void onResponse(Call<ActorsEntity> call, Response<ActorsEntity> response) {
                response.body();
                setData(response.body());
            }

            @Override
            public void onFailure(Call<ActorsEntity> call, Throwable t) {

            }
        });

    }

    private void fetchPhotos(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<ActorsPhotoEntity> call = retrofit.getActorsPhotos(id, "788a71cfbb2953df3cc3b1e7531ef259");
        call.enqueue(new Callback<ActorsPhotoEntity>() {
            @Override
            public void onResponse(Call<ActorsPhotoEntity> call, Response<ActorsPhotoEntity> response) {
                response.body().getProfiles();
                setPhotos(response.body().getProfiles());
            }

            @Override
            public void onFailure(Call<ActorsPhotoEntity> call, Throwable t) {
                System.out.println("hata!!" + t);
            }
        });
    }
    private void setPhotos(ArrayList<ActorsPhotos> list){
        actorsPhotos.addAll(list);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InfoAdapter(getContext(),actorsPhotos);
        recyclerView.setAdapter(adapter);

    }

    private void setData(ActorsEntity list) {
        actorsEntities.add(list);
        setItems(list);
    }

    private void setItems(ActorsEntity list) {
        String date = list.getBirthday();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date newDate = dateFormat.parse(date);
            dateFormat = new SimpleDateFormat("dd MMM yyyy");
            date = dateFormat.format(newDate);
            birthday.setText("Doğum Tarihi: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        birthPlace.setText("Şehri, Ülkesi: " + list.getPlace_of_birth());

        final String bio = list.getBiography();
        isCharacterCountOfOverviewOverflowed = bio.length() > 140;
        if (isCharacterCountOfOverviewOverflowed) {
            biography.setText(bio.substring(0, 137) + "...");
        } else {
            biography.setText(bio);
        }
        biography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCharacterCountOfOverviewOverflowed = !isCharacterCountOfOverviewOverflowed;
                if (isCharacterCountOfOverviewOverflowed) {
                    biography.setText(bio.substring(0, 137) + "...");
                } else {
                    biography.setText(bio);
                }
            }
        });

    }

    public static InfoFragment newInstance(int id) {
        InfoFragment infoFragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        infoFragment.setArguments(bundle);
        return infoFragment;
    }
}
