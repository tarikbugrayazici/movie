package com.example.movies.ui.detail

import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.base.BaseActivity
import com.example.movies.core.util.Constants
import com.example.movies.data.entity.Backdrops
import com.example.movies.data.entity.DetailInfo
import com.example.movies.data.entity.GalleryPhotoModel
import com.example.movies.data.entity.Result
import com.example.movies.data.service.RetroFitService
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*


class DetailActivity : BaseActivity() {

    private var id: Int = 0

    internal var service = RetroFitService()

    override val layoutId: Int?
        get() = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        val i = intent
        id = i.getIntExtra("id", 0)

        setViewPager()
        fetchMovieDetail()
        fetchGetBackDrops()
    }

    internal fun fetchMovieDetail() {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val detailInfoResult: Result<DetailInfo> = result as Result<DetailInfo>
                if (detailInfoResult.data != null) {
                    setData(detailInfoResult.data!!)
                } else {
                    Toast.makeText(this@DetailActivity, detailInfoResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchMovieDetail(id, serviceCallBack)
    }

    internal fun fetchGetBackDrops() {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val galleryPhotoModel: Result<GalleryPhotoModel> = result as Result<GalleryPhotoModel>
                if (galleryPhotoModel.data != null) {
                    setAdapter(galleryPhotoModel.data!!.backdrops)
                } else {
                    Toast.makeText(this@DetailActivity, galleryPhotoModel.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchBackDrops(id, serviceCallBack)
    }

    private fun setData(list: DetailInfo) {
        releaseDate.setText(list.release_date)
        movieName.setText(list.title)
        val title = list.title
        supportActionBar!!.setTitle(title)
        Glide.with(this).load(Constants.IMAGE_BASE_PATH + list.poster_path!!).centerCrop().into(profileImage)

    }

    private fun setAdapter(list: ArrayList<Backdrops>?) {
        val pagerAdapter = PagerAdapterSlide(this, list)
        viewPager.setAdapter(pagerAdapter)
        circularIndicator.setViewPager(viewPager)
    }

    private fun setViewPager() {
        val detailTabAdapter = DetailTabAdapter(supportFragmentManager, this, id)
        viewPagerInfo.setAdapter(detailTabAdapter)
        tabLayout.setupWithViewPager(viewPagerInfo)
    }
}
