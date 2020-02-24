package com.example.movies.ui.actorsDetail

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.Toast
import butterknife.BindView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.core.base.BaseActivity
import com.example.movies.core.helper.ImageHelper
import com.example.movies.data.entity.*
import com.example.movies.data.service.RetroFitService
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_detail_actors.*
import me.relex.circleindicator.CircleIndicator
import java.util.*


class ActorsDetailActivity : BaseActivity() {

    private var id: Int? = null
    private val media = ArrayList<Media>()
    internal var service = RetroFitService()

    override val layoutId: Int?
        get() = R.layout.activity_detail_actors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = intent
        id = i.getIntExtra("actor", 0)
        val actorsDetailTabAdapter = ActorsDetailTabAdapter(supportFragmentManager, this, id!!)
        viewPagerTab!!.adapter = actorsDetailTabAdapter
        tabLayout!!.setupWithViewPager(viewPagerTab)
        fetchActorsDetail(id!!)
        fetchActorsPictures()
    }

    private fun fetchActorsPictures() {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val personPResult : Result<PersonP> = result as Result<PersonP>
                if (personPResult.data != null) {
                    setProfilePicture(result.data!!)
                } else {
                    Toast.makeText(this@ActorsDetailActivity, personPResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchActorsPictures(id!!, serviceCallBack)
    }

    private fun fetchActorsDetail(id: Int) {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val actorsTaggedImages : Result<ActorsTaggedImages> = result as Result<ActorsTaggedImages>
                if (actorsTaggedImages.data != null) {
                    setData(actorsTaggedImages.data!!.results)
                } else {
                    Toast.makeText(this@ActorsDetailActivity, actorsTaggedImages.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchActorsDetail(id, serviceCallBack)
    }

    private fun setProfilePicture(list: PersonP) {
        Glide.with(this)
                .load(ImageHelper.getImageUrl(list.profile_path!!))
                .centerCrop()
                .into(imageView!!)
    }


    private fun setData(list: ArrayList<ActorsResult>?) {
        setMedia(list!!)
    }

    private fun setMedia(list: ArrayList<ActorsResult>) {
        for (medial in list) {
            medial.media?.let { media.add(it) }
            val pagerAdapterSlideActor = PagerAdapterSlideActor(this, media)
            viewPagerActor!!.adapter = pagerAdapterSlideActor
            circleIndicator!!.setViewPager(viewPagerActor)

        }
    }
}