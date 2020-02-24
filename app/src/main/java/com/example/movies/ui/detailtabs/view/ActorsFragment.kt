package com.example.movies.ui.detailtabs.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.example.movies.R
import com.example.movies.core.base.BaseFragment
import com.example.movies.data.entity.Cast
import com.example.movies.data.entity.MovieCrew
import com.example.movies.data.entity.Result
import com.example.movies.data.enums.Sorting
import com.example.movies.data.service.RetroFitService
import com.example.movies.ui.detailtabs.adapter.ActorsAdapter

import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

import butterknife.BindView
import kotlinx.android.synthetic.main.actors.*

class ActorsFragment : BaseFragment(), Dialog.DialogListener {

    private var adapter: ActorsAdapter? = null
    private val casts = ArrayList<Cast>()
    private var layoutManager: LinearLayoutManager? = null
    private var movieId: Int? = null
    internal var service = RetroFitService()

    override fun getFragmentLayoutId(): Int {
        return R.layout.actors
    }

    override fun onFinishDialog(inputText: Int) {
        val list = ArrayList(sortedList(inputText))
        adapter = ActorsAdapter(context!!, list)
        recycler_view_person!!.adapter = adapter
    }

    private fun sortedList(type: Int): ArrayList<Cast> {
        val castSorted = ArrayList(casts)
        if (type == Sorting.BY_NAME.value) {
            Collections.sort(castSorted) { (_, _, _, _, _, name2),
                                           (_, _, _, _, _, name) -> name!!.compareTo(name) }

        } else if (type == Sorting.BY_CHARACTER.value) {
            Collections.sort(castSorted) { (_, character2), (_, character) -> character!!.compareTo(character) }
        }

        return castSorted
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sort!!.setOnClickListener { openDialog() }
        if (arguments != null) {
            movieId = arguments!!.getInt("id")
            setRecyclerView()
            fetchActorMovies(id)
        }
    }

    private fun openDialog() {
        val dialog = Dialog()
        dialog.setTargetFragment(this@ActorsFragment, 1)
        dialog.show(fragmentManager!!, "dialog")
    }

    private fun fetchActorMovies(id: Int) {
        val serviceCallBack = object : RetroFitService.ResultCallBack {
            override fun getResult(result: Result<*>) {
                val movieCrewResult : Result<MovieCrew> = result as Result<MovieCrew>
                if (movieCrewResult.data != null) {
                    setData(movieCrewResult.data!!.cast)
                } else {
                    Toast.makeText(activity, movieCrewResult.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
        service.fetchActorMovies(id, serviceCallBack)
    }

    private fun setRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        recycler_view_person!!.layoutManager = layoutManager

        val sharedPreferences = context!!.getSharedPreferences("sorting", Context.MODE_PRIVATE)
        val sortType = sharedPreferences.getInt("key", Sorting.BY_RECOMMENDED.value)
        val list = ArrayList(sortedList(sortType))

        adapter = ActorsAdapter(context!!, list)
        recycler_view_person!!.adapter = adapter
    }

    private fun setData(list: ArrayList<Cast>) {
        casts.addAll(list)
        number_of_actors!!.text = casts.size.toString() + " kişi"
        setRecyclerView()

    }

    companion object {

        fun newInstance(id: Int): ActorsFragment {
            val fragment = ActorsFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment

        }
    }
}
