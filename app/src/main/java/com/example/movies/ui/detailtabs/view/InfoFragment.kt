package com.example.movies.ui.detailtabs.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.core.helper.DateFormatHelper
import com.example.movies.core.helper.LanguageHelper
import com.example.movies.core.helper.NumberFormatHelper
import com.example.movies.data.entity.*
import com.example.movies.data.service.RetroFitService
import com.example.movies.ui.detailtabs.adapter.InfoAdapter
import kotlinx.android.synthetic.main.tab_bilgiler_item.*
import java.util.*

class InfoFragment : BaseFragment() {
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: InfoAdapter? = null
    private var trailer: ArrayList<Trailer>? = null
    private var movieCrews: ArrayList<MovieCrew?>? = null
    private var detailInfos: ArrayList<DetailInfo?>? = null
    private var genres: ArrayList<Genres>? = null
    private var productionCompanies: ArrayList<ProductionCompanies>? = null
    private var productionCountriess: ArrayList<ProductionCountries>? = null
    private var isCharacterCountOfOverviewOverflowed = false
    internal var service = RetroFitService()


    override fun getFragmentLayoutId(): Int {
        return R.layout.tab_bilgiler_item
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val id = arguments!!.getInt("id")
            fetchInfo(id)
            fetchCrews(id)
            fetchTrailers(id)
        }
    }

    private fun fetchTrailers(id: Int) {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val trailerResult: Result<Trailers> = result as Result<Trailers>
                if (trailerResult.data != null) {
                    setVideos(trailerResult.data!!.results)
                } else {
                    Toast.makeText(activity, trailerResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchTrailers(id, serviceCallBack)
    }

    private fun fetchInfo(id: Int) { //419704
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val detailInfoResult: Result<DetailInfo> = result as Result<DetailInfo>
                if (detailInfoResult.data != null) {
                    setData(detailInfoResult.data)
                } else {
                    Toast.makeText(activity, detailInfoResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchInfo(id, serviceCallBack)
    }

    private fun fetchCrews(id: Int) {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val movieCrewResult: Result<MovieCrew> = result as Result<MovieCrew>
                if (movieCrewResult.data != null) {
                    setDataCrews(movieCrewResult.data)
                } else {
                    Toast.makeText(activity, movieCrewResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchCrews(id, serviceCallBack)

    }

    private fun setVideos(list: ArrayList<Trailer>?) {
        trailer = ArrayList()
        trailer!!.addAll(list!!)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        info_recycler_view!!.layoutManager = layoutManager
        adapter = InfoAdapter(context!!, trailer!!)
        info_recycler_view!!.adapter = adapter
    }

    private fun setDataCrews(list: MovieCrew?) {
        movieCrews = ArrayList()
        movieCrews!!.add(list)
        setTexts(list!!.crew)
    }

    private fun setTexts(crews: ArrayList<Crew>) {
        var crew = arrayOfNulls<String>(crews.size)
        var crew2 = arrayOfNulls<String>(crews.size)
        var liste = ""
        var liste2 = ""
        for ((_, department, _, _, _, name) in crews) {
            liste += name!! + ","
            liste2 += department!! + ","
        }
        for (i in crews.indices) {
            crew = liste.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            crew2 = liste2.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }
        if (crews.size > 3) {
            crew_one!!.text = crew[0]
            crew_ones_two!!.text = crew2[0]
            crew_two!!.text = crew[1]
            crew_twos_two!!.text = crew2[1]
            crew_three!!.text = crew[2]
            crew_threes_three!!.text = crew2[2]
            crew_four!!.text = crew[3]
            crew_fours_four!!.text = crew2[3]
        } else {
            crew_one!!.text = "empty"
            crew_ones_two!!.text = "empty"
            crew_two!!.text = "empty"
            crew_twos_two!!.text = "empty"
            crew_three!!.text = "empty"
            crew_threes_three!!.text = "empty"
            crew_four!!.text = "empty"
            crew_fours_four!!.text = "empty"
        }

        show_all!!.setOnClickListener {
            val manager = fragmentManager
            val ft = manager!!.beginTransaction()
            val prev = manager.findFragmentByTag("crews")
            if (prev != null) {
                ft.remove(prev)
            }
            val newFragment = CrewsDialogFragment.newInstance(crews)
            newFragment.show(ft, "crews")
        }
    }

    private fun setData(list: DetailInfo?) {
        detailInfos = ArrayList()
        detailInfos!!.add(list)
        setItems(list!!)
        setTag(list, list.production_companies, list.production_countries)
        list.genres
        setGenres(list.genres)
    }

    private fun setTag(list: DetailInfo, productionCompaniesArrayList: ArrayList<ProductionCompanies>?,
                       productionCountriesArrayList: ArrayList<ProductionCountries>?) {
        productionCompanies = ArrayList()
        productionCompanies!!.addAll(productionCompaniesArrayList!!)
        productionCountriess = ArrayList()
        productionCountriess!!.addAll(productionCountriesArrayList!!)
        original_name!!.text = list.original_title
        release_date1!!.text = DateFormatHelper.formatDate(list.release_date!!)
        situtation!!.text = list.status

        var companiess = " "
        for (s in productionCompanies!!) {
            s.toString()
            companiess += s.name!! + ","
        }
        companies!!.text = companiess
        budget!!.text = NumberFormatHelper.formatNumber(list.budget.toDouble())
        runtime!!.text = list.runtime.toString() + " dk"

        original_language!!.text = LanguageHelper.formatLanguage(list.original_language!!)
        revenues!!.text = NumberFormatHelper.formatNumber(list.revenue.toDouble())
        var countries = " "
        for ((_, name) in productionCountriess!!) {
            countries += name!! + ","
        }
        production_countries!!.text = countries

        homepage!!.text = list.homepage
        homepage!!.setOnClickListener { v ->
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse(list.homepage))
            v.context.startActivity(intent)
        }

    }

    private fun setGenres(list: ArrayList<Genres>?) {
        genres = ArrayList()
        genres!!.addAll(list!!)
        var liste = " "
        for (s in genres!!) {
            s.toString()
            liste += s.name!! + " "
        }
        text_genres!!.text = liste
    }

    private fun setItems(list: DetailInfo) {
        text_rating!!.text = list.vote_average.toString()
        val overview = list.overview
        isCharacterCountOfOverviewOverflowed = overview!!.length > 140
        setOverviewText(overview)
        text_overview!!.setOnClickListener {
            isCharacterCountOfOverviewOverflowed = !isCharacterCountOfOverviewOverflowed
            setOverviewText(overview)
        }
    }

    private fun setOverviewText(overview: String?) {
        if (isCharacterCountOfOverviewOverflowed) {
            text_overview!!.text = overview!!.substring(0, 137) + "..."
        } else {
            text_overview!!.text = overview
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
