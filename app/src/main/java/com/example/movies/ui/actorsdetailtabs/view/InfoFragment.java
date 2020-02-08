package com.example.movies.ui.actorsdetailtabs.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies.R;
import com.example.movies.core.base.BaseFragment;
import com.example.movies.data.entity.ActorsEntity;
import com.example.movies.data.entity.ActorsPhotoEntity;
import com.example.movies.data.entity.ActorsPhotos;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;
import com.example.movies.ui.actorsdetailtabs.adapter.InfoAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;

public class InfoFragment extends BaseFragment {
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.birth_place)
    TextView birthPlace;
    @BindView(R.id.known_name)
    TextView knownName;
    @BindView(R.id.biography)
    TextView biography;
    @BindView(R.id.actors_photo_recyclerview)
    RecyclerView actorsPhotoRecyclerview;
    private ArrayList<ActorsPhotos> actorsPhotos = new ArrayList<>();
    private InfoAdapter adapter;
    private LinearLayoutManager layoutManager;
    private String DATE_FORMAT = "yyyy-MM-dd";
    private ArrayList<ActorsEntity> actorsEntities = new ArrayList<>();
    private int id;
    private boolean isCharacterCountOfOverviewOverflowed = false;
    RetroFitService service = new RetroFitService();

    @Override
    public Integer getFragmentLayoutId() {
        return R.layout.actors_detail_info_items;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
            service(id);
            fetchPhotos(id);
        }
    }

    private void service(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<ActorsEntity> actorsEntityResult = result;
                if (actorsEntityResult.getData() != null) {
                    setData(actorsEntityResult.getData());
                } else {
                    Toast.makeText(getActivity(), result.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.service(id, serviceCallBack);
    }

    private void fetchPhotos(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<ActorsPhotoEntity> actorsPhotoEntityResult = result;
                if (actorsPhotoEntityResult.getData() != null) {
                    setPhotos(actorsPhotoEntityResult.getData().getProfiles());
                } else {
                    Toast.makeText(getActivity(), result.getErrorMessage(), Toast.LENGTH_LONG).show();

                }
            }
        };
        service.fetchPhotos(id, serviceCallBack);
    }

    private void setPhotos(ArrayList<ActorsPhotos> list) {
        actorsPhotos.addAll(list);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        actorsPhotoRecyclerview.setLayoutManager(layoutManager);
        adapter = new InfoAdapter(getContext(), actorsPhotos);
        actorsPhotoRecyclerview.setAdapter(adapter);
    }

    private void setData(ActorsEntity list) {
        actorsEntities.add(list);
        setItems(list);
    }

    private void setItems(ActorsEntity list) {
        if (list.getBiography() != null && list.getBirthday() != null) {
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
        } else {
            birthday.setText("empty");
            birthPlace.setText("empty");
            biography.setText("empty");
        }
    }

    public static InfoFragment newInstance(int id) {
        InfoFragment infoFragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        infoFragment.setArguments(bundle);
        return infoFragment;
    }
}
