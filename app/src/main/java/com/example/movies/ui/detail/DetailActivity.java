package com.example.movies.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.core.base.BaseActivity;
import com.example.movies.core.util.Constants;
import com.example.movies.data.entity.Backdrops;
import com.example.movies.data.entity.DetailInfo;
import com.example.movies.data.entity.GalleryPhotoModel;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;

import java.util.ArrayList;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;


public class DetailActivity extends BaseActivity {

    @BindView(R.id.info_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.circularIndicator)
    CircleIndicator circularIndicator;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.movie_name)
    TextView movieName;
    @BindView(R.id.view_pager_info)
    ViewPager viewPagerInfo;

    private int id;

    RetroFitService service = new RetroFitService();

    @Override
    public Integer getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        id = i.getIntExtra("id", 0);

        setViewPager();
        fetchMovieDetail();
        fetchGetBackDrops();
    }

    void fetchMovieDetail() {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<DetailInfo> detailInfoResult = result;
                if (detailInfoResult.getData() != null) {
                    setData(detailInfoResult.getData());
                } else {
                    Toast.makeText(DetailActivity.this, result.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchMovieDetail(id, serviceCallBack);
    }

    void fetchGetBackDrops() {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<GalleryPhotoModel> galleryPhotoModelResult = result;

                if (galleryPhotoModelResult.getData() != null) {
                    setAdapter(galleryPhotoModelResult.getData().getBackdrops());
                } else {
                    Toast.makeText(DetailActivity.this, result.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchBackDrops(id, serviceCallBack);
    }

    private void setData(DetailInfo list) {
        releaseDate.setText(list.getRelease_date());
        movieName.setText(list.getTitle());
        String title = list.getTitle();
        getSupportActionBar().setTitle(title);
        Glide.with(this).load(Constants.IMAGE_BASE_PATH + list.getPoster_path()).centerCrop().into(profileImage);

    }

    private void setAdapter(ArrayList<Backdrops> list) {
        PagerAdapter pagerAdapter = new PagerAdapterSlide(this, list);
        viewPager.setAdapter(pagerAdapter);
        circularIndicator.setViewPager(viewPager);
    }

    private void setViewPager() {
        DetailTabAdapter detailTabAdapter = new DetailTabAdapter(getSupportFragmentManager(), this, id);
        viewPagerInfo.setAdapter(detailTabAdapter);
        tabLayout.setupWithViewPager(viewPagerInfo);
    }
}
