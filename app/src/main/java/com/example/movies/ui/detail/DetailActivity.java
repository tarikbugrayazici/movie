package com.example.movies.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.Backdrops;
import com.example.movies.data.entity.DetailInfo;
import com.example.movies.data.entity.GalleryPhotoModel;
import com.example.movies.data.service.API;
import com.example.movies.data.service.RetroFitService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.info_tab_layout)
    TabLayout tabLayout;

    private ImageView imageView;
    private ViewPager viewPager, viewPagerInfo;
    private CircleIndicator circleIndicator;
    private TextView textViewMovie, textViewContent, textViewDate;
    private Toolbar toolbar;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent i = getIntent();
        id = i.getIntExtra("id", 0);


        viewPagerInfo = findViewById(R.id.view_pager_info);
        DetailTabAdapter detailTabAdapter = new DetailTabAdapter(getSupportFragmentManager(), this, id);
        viewPagerInfo.setAdapter(detailTabAdapter);
        tabLayout.setupWithViewPager(viewPagerInfo);

        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circularIndicator);
        imageView = findViewById(R.id.profile_image);
        textViewMovie = findViewById(R.id.movie_name);
        textViewContent = findViewById(R.id.content);
        textViewDate = findViewById(R.id.release_date);


        service();
        fetchMovieDetail(id);

    }

    private void fetchMovieDetail(int id) {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<DetailInfo> call = retrofit.getMovieDetail(id,
                "788a71cfbb2953df3cc3b1e7531ef259", "en_US");
        call.enqueue(new Callback<DetailInfo>() {
            @Override
            public void onResponse(Call<DetailInfo> call, Response<DetailInfo> response) {
                setData(response.body());
            }

            @Override
            public void onFailure(Call<DetailInfo> call, Throwable t) {

            }
        });
    }

    private void service() {
        API retrofit = RetroFitService.getRetrofit().create(API.class);
        Call<GalleryPhotoModel> call = retrofit.getBackdrops(id, "788a71cfbb2953df3cc3b1e7531ef259",
                "en-US", "en,null");
        call.enqueue(new Callback<GalleryPhotoModel>() {
            @Override
            public void onResponse(Call<GalleryPhotoModel> call, Response<GalleryPhotoModel> response) {
                response.body().getBackdrops();
                setAdapter(response.body().getBackdrops());
            }

            @Override
            public void onFailure(Call<GalleryPhotoModel> call, Throwable t) {
                System.out.println("hata" + t);
            }
        });
    }

    private void setData(DetailInfo list) {
        textViewDate.setText(list.getRelease_date());
        textViewMovie.setText(list.getTitle());
        String title = list.getTitle();
        getSupportActionBar().setTitle(title);
        String url = "https://image.tmdb.org/t/p/w500";
        Glide.with(this).load(url + list.getPoster_path()).centerCrop().into(imageView);

    }

    private void setAdapter(ArrayList<Backdrops> list) {
        PagerAdapter pagerAdapter = new PagerAdapterSlide(this, list);
        viewPager.setAdapter(pagerAdapter);
        circleIndicator.setViewPager(viewPager);
    }
}
