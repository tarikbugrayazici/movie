package com.example.movies.ui.actorsdetailtabs.view

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.data.entity.ActorsEntity
import com.example.movies.data.entity.ActorsPhotoEntity
import com.example.movies.data.entity.ActorsPhotos
import com.example.movies.data.entity.Result
import com.example.movies.data.service.RetroFitService
import com.example.movies.ui.actorsdetailtabs.adapter.InfoAdapter
import kotlinx.android.synthetic.main.actors_detail_info_items.*
import java.util.ArrayList

class InfoFragment : BaseFragment() {

    private var adapter: InfoAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var movieId: Int? = null;
    private var isCharacterCountOfOverviewOverflowed = false
    var service = RetroFitService()

    override fun getFragmentLayoutId(): Int {
        return R.layout.actors_detail_info_items
    }

    override fun onViewCreated(@NonNull view: View, @NonNull savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null)
            movieId = arguments!!.getInt("id")
        service(movieId!!)
        fetchPhotos(movieId!!)
    }

    private fun service(id: Int) {
        val serviceCallBack: RetroFitService.ResultCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val actorsEntityResult: Result<ActorsEntity> = result as Result<ActorsEntity>
                if (actorsEntityResult.data != null)
                    setData(actorsEntityResult.data)
                else
                    Toast.makeText(getActivity(), actorsEntityResult.errorMessage, Toast.LENGTH_LONG).show();
            }
        }
        service.service(id, serviceCallBack)
    }

    private fun fetchPhotos(id: Int) {
        val serviceCallBack: RetroFitService.ResultCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val actorsPhotoEntityResult: Result<ActorsPhotoEntity> = result as Result<ActorsPhotoEntity>
                if (actorsPhotoEntityResult.data != null)
                    setPhotos(actorsPhotoEntityResult.data!!.profiles)
                else
                    Toast.makeText(getActivity(), actorsPhotoEntityResult.errorMessage, Toast.LENGTH_LONG).show();
            }
        }
        service.fetchPhotos(id, serviceCallBack)
    }

    private fun setPhotos(list: ArrayList<ActorsPhotos>?) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        actors_photo_recyclerview.layoutManager = layoutManager
        adapter = InfoAdapter(context!!, list)
        actors_photo_recyclerview.adapter = adapter
    }

    private fun setData(list: ActorsEntity?) {
        setItems(list)
    }

    private fun setItems(list: ActorsEntity?) {
        if (list!!.biography != null && list!!.birthday != null) {
            // birth_place.text(DateFormatHelper.formatDate(list.place_of_birth))
            var bio = list.biography
            isCharacterCountOfOverviewOverflowed = bio!!.length > 140
            setBiographyText(bio)
            biography.setOnClickListener {
                isCharacterCountOfOverviewOverflowed = !isCharacterCountOfOverviewOverflowed
                setBiographyText(bio)
            }
        } else {
            birthday.text = "empty"
            birth_place.text = "empty"
            biography.text = "empty"
        }
    }

    private fun setBiographyText(bio: String?) {
        if (isCharacterCountOfOverviewOverflowed) {
            biography!!.text = bio!!.substring(0, 137) + "..."
        } else {
            biography!!.text = bio
        }
    }

    companion object {

        fun newInstance(id: Int): InfoFragment {
            val f = InfoFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            f.arguments = bundle
            return f
        }
    }

}

