package com.example.movies.ui.actorsDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.core.base.BaseActivity;
import com.example.movies.core.helper.ImageHelper;
import com.example.movies.data.entity.ActorsResult;
import com.example.movies.data.entity.ActorsTaggedImages;
import com.example.movies.data.entity.Media;
import com.example.movies.data.entity.PersonP;
import com.example.movies.data.entity.Result;
import com.example.movies.data.service.RetroFitService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;


public class ActorsDetailActivity extends BaseActivity {
    @BindView(R.id.circular_image_actor)
    ImageView imageView;
    @BindView(R.id.view_pager_actor_tab)
    ViewPager viewPagerTab;
    @BindView(R.id.view_pager_actor)
    ViewPager viewPagerActor;
    @BindView(R.id.tab_layout_actors)
    TabLayout tabLayout;
    @BindView(R.id.circular_indicator)
    CircleIndicator circleIndicator;
    private int id;
    private ArrayList<Media> media = new ArrayList<>();
    RetroFitService service = new RetroFitService();

    @Override
    public Integer getLayoutId() {
        return R.layout.activity_detail_actors;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent i = getIntent();
        id = i.getIntExtra("actor", 0);
        ActorsDetailTabAdapter actorsDetailTabAdapter = new ActorsDetailTabAdapter(getSupportFragmentManager(), this, id);
        viewPagerTab.setAdapter(actorsDetailTabAdapter);
        tabLayout.setupWithViewPager(viewPagerTab);
        fetchActorsDetail(id);
        fetchActorsPictures();
    }

    private void fetchActorsPictures() {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<PersonP> personPResult = result;
                if (personPResult.getData() != null) {
                    setProfilePicture(personPResult.getData());
                } else {
                    Toast.makeText(ActorsDetailActivity.this, result.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchActorsPictures(id, serviceCallBack);
    }

    private void fetchActorsDetail(int id) {
        RetroFitService.ResultCallBack serviceCallBack = new RetroFitService.ResultCallBack() {
            @Override
            public void getResult(Result result) {
                Result<ActorsTaggedImages> actorsTaggedImagesResult = result;
                if (actorsTaggedImagesResult.getData() != null) {
                    setData(actorsTaggedImagesResult.getData().getResults());
                } else {
                    Toast.makeText(ActorsDetailActivity.this, result.getErrorMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };
        service.fetchActorsDetail(id, serviceCallBack);
    }

    private void setProfilePicture(PersonP list) {
        Glide.with(this)
                .load(ImageHelper.INSTANCE.getImageUrl(list.getProfile_path()))
                .centerCrop()
                .into(imageView);
    }


    private void setData(ArrayList<ActorsResult> list) {
        setMedia(list);
    }

    private void setMedia(ArrayList<ActorsResult> list) {

        for (ActorsResult s : list) {
            media.add(s.getMedia());
            PagerAdapterSlideActor pagerAdapterSlideActor = new
                    PagerAdapterSlideActor(this, media);
            viewPagerActor.setAdapter(pagerAdapterSlideActor);
            circleIndicator.setViewPager(viewPagerActor);

        }
    }
}
