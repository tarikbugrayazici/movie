package com.example.movies.ui.actorsDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.movies.R;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;

import retrofit2.Call;


public class ActorsDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPagerTab, viewPager;
    private ImageView imageView;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actors);
        Intent i = getIntent();
        id = i.getIntExtra("actor", 0);


        viewPagerTab = findViewById(R.id.view_pager_actors);
        tabLayout = findViewById(R.id.tab_layout_actors);
        ActorsDetailTabAdapter actorsDetailTabAdapter = new ActorsDetailTabAdapter(getSupportFragmentManager(), this, id);
        viewPagerTab.setAdapter(actorsDetailTabAdapter);
        tabLayout.setupWithViewPager(viewPagerTab);

        imageView = findViewById(R.id.circular_image_actor);
        fetchActorsDetail(id);

    }
    private void fetchActorsDetail(int id){
        API retrofit = RetroFitService.getRetrofit().create(API.class);
    }



}
